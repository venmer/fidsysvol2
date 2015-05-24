package web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import web.elements.Register;

/**
 * autor:maksim
 * date: 19.05.15
 * time: 23:30.
 */
public class RegisterPage extends AbstractPage {
    @FindBy(className = "register-form")
    private Register registerForm;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public void open() {
        getMainMenu().getUserRegister().click();
    }

    public void register(String name, String login, String email, String pass) {
        registerForm.register(name, login, email, pass);
    }

}
