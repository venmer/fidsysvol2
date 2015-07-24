package web.steps;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import web.page.ProfilePage;
import web.page.RegisterPage;
import web.page.SignInPage;

import java.net.MalformedURLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * autor:maksim
 * date: 19.05.15
 * time: 18:09.
 */
@Ignore
@Features("FidSys welcome page")
public class FidSysWebTest {
    private WebDriver driver;
    private final String URL = "http://localhost:8081/";
    private final String USER_NAME = "admin";
    private final String USER_LOGIN = "login";
    private final String USER_EMAIL = "email@email";
    private final String USER_PASS = "pass";
    private final String WELCOME_PAGE = "Welcome to FidSys";
    private RegisterPage registerPage;
    private SignInPage signInPage;
    private ProfilePage profilePage;

    @Before
    public void createDriver() throws MalformedURLException {
        //   System.setProperty("webdriver.chrome.driver", "/home/maksim/selenium/chromedriver");
        driver = new PhantomJSDriver();
        registerPage = new RegisterPage(driver);
        signInPage = new SignInPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test
    public void openMainPage() throws InterruptedException {
        openIndexPage();
        openRegistrationForm();
        fillCells();
        openSignInForm();
        signInUser();
        checkUserSignIn();
        openProfilePage();
        checkProfileName();
        checkProfileLogin();
        checkProfileEmail();
        userLogout();
        Thread.sleep(5000);
    }

    @Step("Open main page")
    public void openIndexPage() {
        driver.get(URL);
        registerPage.getMainMenu().getIndex().click();
        assertThat(registerPage.getMainMenu().getIndex().getText(), is("FidSys"));
    }

    @Step("Open registration  modal")
    public void openRegistrationForm() throws InterruptedException {
        registerPage.open();
    }

    @Step("fill the registration fields")
    public void fillCells() {
        registerPage.register(USER_NAME, USER_LOGIN, USER_EMAIL, USER_PASS);
        assertThat(driver.getCurrentUrl(), is(URL));
    }

    @Step("open signIn modal")
    public void openSignInForm() {
        signInPage.open();
    }

    @Step("user signin")
    public void signInUser() {
        signInPage.signIn(USER_LOGIN, USER_PASS);
        assertThat(driver.getCurrentUrl(), is(URL));
    }

    @Step("check is user signin?")
    public void checkUserSignIn() {
        assertThat(signInPage.getMainMenu().getUserMenu().getText(), is(USER_LOGIN));
    }

    @Step("open profile page")
    public void openProfilePage() {
        profilePage.getMainMenu().getProfile().click();
        assertThat(driver.getTitle(), is("FidSys." + USER_LOGIN));
    }

    @Step("check profile Name")
    public void checkProfileName() {
        assertThat(profilePage.getProfile().getProfileName().getText(), is(USER_NAME));
    }

    @Step("check profile Login")
    public void checkProfileLogin() {
        assertThat(profilePage.getProfile().getProfileLogin().getText(), is(USER_LOGIN));
    }

    @Step("check profile email")
    public void checkProfileEmail() {
        assertThat(profilePage.getProfile().getProfileEmail().getText(), is(USER_EMAIL));
    }

    @Step("logout")
    public void userLogout() {
        profilePage.getMainMenu().getUserMenu().click();
        profilePage.getMainMenu().getUserSignout().click();
    }

    @After
    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
