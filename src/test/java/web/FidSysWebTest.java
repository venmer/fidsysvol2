package web;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * autor:maksim
 * date: 19.05.15
 * time: 18:09.
 */
@Features("Fidsys main page")
public class FidSysWebTest {
   private WebDriver driver;
    @Before
    public void createDriver(){
        driver=new PhantomJSDriver();
    }

    @Stories("is main page is available")
    @Test
    public void openMainPage(){
        openPage("http://localhost:8081");
    }
    @Step("Open {0}")
    public void openPage(String url){
        driver.get(url);
    }
}
