package web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import web.elements.SignIn;

/**
 * autor:maksim
 * date: 24.05.15
 * time: 13:22.
 */
public class SignInPage extends AbstractPage {
    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    @FindBy(className = "signin-form")
    private SignIn signInPage;

    public void open() {
        getMainMenu().getUserSignin().click();
    }

    public void signIn(String login, String pass) {
        signInPage.signIn(login, pass);
    }
}
