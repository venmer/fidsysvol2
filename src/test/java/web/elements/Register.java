package web.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * autor:maksim
 * date: 19.05.15
 * time: 23:32.
 */
public class Register extends HtmlElement {
    @FindBy(id = "name")
    private TextInput regName;
    @FindBy(id = "login")
    private TextInput login;
    @FindBy(id = "email")
    private TextInput email;
    @FindBy(id = "register-pass")
    private TextInput pass;
    @FindBy(id = "register-submit")
    private Button submit;

    public TextInput getRegName() {
        return regName;
    }

    public void setRegName(TextInput regName) {
        this.regName = regName;
    }

    public TextInput getLogin() {
        return login;
    }

    public void setLogin(TextInput login) {
        this.login = login;
    }

    public TextInput getEmail() {
        return email;
    }

    public void setEmail(TextInput email) {
        this.email = email;
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

    public void register(String name, String login, String email, String pass) {
        getRegName().sendKeys(name);
        getLogin().sendKeys(login);
        getEmail().sendKeys(email);
        getPass().sendKeys(pass);
        getSubmit().click();

    }
}
