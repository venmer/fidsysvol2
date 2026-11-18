# FIDsys

FIDsys — веб-система идентификации fiducial-меток (защитных знаков) для проверки подлинности товаров.

Пользователь наводит веб-камеру на метку, система находит опорные точки, вычисляет углы между
векторами и сравнивает полученный «отпечаток» с эталоном, сохранённым в графовой базе.
По результату сравнения метка признаётся оригинальной или поддельной. Пользователи и история
проверок хранятся в MongoDB.

## Как это работает

1. **Кодирование (codify)** — с изображения снимаются точки метки, точки кластеризуются
   (радиус `60 px`), по ним строятся векторы и вычисляются углы между ними. Углы и связи
   между ними сохраняются в Neo4j как граф (узлы `INTERVALS`, связи `LEVEL`).
2. **Идентификация (identify)** — для новой метки вычисляются углы, затем в Neo4j ищется
   наиболее длинная совпадающая цепочка уровней. Если расхождение укладывается в допуск
   (`±1°`), метка считается оригинальной.
3. **Асинхронность** — обе операции выполняются в фоновом потоке; статус проверки
   (`RUNNING` / `READY`) и результат (`ORIGIN` / `UNKNOWN`) доступны через
   `GET /identifier/status/{id}`.

## Технологии

- Java 7, Maven (WAR)
- Jersey 2 (JAX-RS), Freemarker MVC, Jackson
- Neo4j 2.0 (хранение эталонных графов, доступ через `neo4j-jdbc`)
- MongoDB + Morphia (пользователи и результаты проверок)
- Jetty (запуск и деплой), Log4j
- Тесты: JUnit, Jersey Test Framework, Mockito, Selenium/PhantomJS, Allure

## Требования

- JDK 7+
- Maven 3
- Запущенные MongoDB (`localhost:27017`) и Neo4j (`localhost:7474`)

## Конфигурация

Параметры подключения лежат в `src/main/resources/<environment>.properties`
(по умолчанию `dev.properties`):

```properties
mongo.host=localhost
mongo.port=27017
mongo.database=fidsys

neo4j.host=localhost
neo4j.port=7474
```

Файл выбирается системным свойством `environment` (`dev`, `prod`, ...). В `jetty-maven-plugin`
оно выставлено в `dev`.

## Сборка и запуск

```bash
mvn clean package          # собрать WAR (target/spticad.war)
mvn clean jetty:run        # запустить локально
```

Приложение поднимается на порту Jetty по умолчанию (`8080`) с контекстом `/`.

Для деплоя на Heroku при сборке копируется `jetty-runner.jar` (см. плагин
`maven-dependency-plugin`).

### Docker

```bash
docker compose up --build      # приложение + MongoDB + Neo4j
```

Приложение будет доступно на `http://localhost:8080`. В контейнере используется
профиль `docker` (`src/main/resources/docker.properties`), в котором БД адресуются
по именам сервисов `mongo` и `neo4j`. Данные БД хранятся в томах `mongo-data`
и `neo4j-data`.

## HTTP API

| Метод | Путь | Описание |
|-------|------|----------|
| `GET` | `/` | Главная страница |
| `GET` | `/profile` | Профиль пользователя |
| `GET` | `/try`, `/try/desc` | Демо-страница работы с камерой |
| `POST` | `/auth/register` | Регистрация (`login`, `email`, `name`, `password`) |
| `POST` | `/auth/signin` | Вход (`signin-login`, `signin-pass`) |
| `GET` | `/auth/signout` | Выход |
| `POST` | `/coder/codify` | Кодирование метки |
| `POST` | `/identifier/identify` | Идентификация метки |
| `GET` | `/identifier/status/{id}` | Статус и результаты проверок |

Формат тела запроса для `codify` и `identify` (`application/json`):

```json
{
  "id": "user-id",
  "points": [
    {"x": 120, "y": 240},
    {"x": 310, "y": 255}
  ]
}
```

Для `identify` поле `id` обязательно. Допускается не более 8 точек — иначе возвращается
`400 Bad Request`.

## Структура проекта

```
src/main/java/ru/mremne/
├── resources/     # REST-ресурсы (Jersey)
├── service/       # бизнес-логика (FidService, MongoService)
├── model/         # доменные модели и Morphia-сущности
├── executor/      # выполнение Cypher-запросов к Neo4j
├── auth/          # SecurityContext / аутентификация
└── config/        # конфигурация подключений
src/main/webapp/
├── templates/     # Freemarker-шаблоны
├── layout/        # общий layout
└── public/        # статика (JS: jsfeat, main.js; CSS, шрифты)
```

## Тесты

```bash
mvn test
```

Ресурсы покрыты тестами на Jersey Test Framework с мок-сервисами
(`CodifyResourceTest`, `IdentifyResourceTest`), UI-сценарии — Selenium (`FidSysWebTest`).
