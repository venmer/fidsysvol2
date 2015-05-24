package web.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * autor:maksim
 * date: 24.05.15
 * time: 13:15.
 */
public class SignIn extends HtmlElement {
    @FindBy(id = "signin-login")
    private TextInput login;
    @FindBy(id = "signin-pass")
    private TextInput pass;
    @FindBy(id = "signin-submit")
    private Button submit;

    public TextInput getLogin() {
        return login;
    }

    public void setLogin(TextInput login) {
        this.login = login;
    }

    public TextInput getPass() {
        return pass;
    }

    public void setPass(TextInput pass) {
        this.pass = pass;
    }

    public Button getSubmit() {
        return submit;
    }

    public void setSubmit(Button submit) {
        this.submit = submit;
    }

    public void signIn(String login, String pass) {
        getLogin().sendKeys(login);
        getPass().sendKeys(pass);
        getSubmit().click();
    }
}
