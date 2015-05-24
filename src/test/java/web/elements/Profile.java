package web.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * autor:maksim
 * date: 24.05.15
 * time: 13:51.
 */
public class Profile extends HtmlElement {
    @FindBy(id = "profile-name")
    private HtmlElement profileName;
    @FindBy(id = "profile-login")
    private HtmlElement profileLogin;
    @FindBy(id = "profile-email")
    private HtmlElement profileEmail;

    public HtmlElement getProfileName() {
        return profileName;
    }

    public void setProfileName(HtmlElement profileName) {
        this.profileName = profileName;
    }

    public HtmlElement getProfileLogin() {
        return profileLogin;
    }

    public void setProfileLogin(HtmlElement profileLogin) {
        this.profileLogin = profileLogin;
    }

    public HtmlElement getProfileEmail() {
        return profileEmail;
    }

    public void setProfileEmail(HtmlElement profileEmail) {
        this.profileEmail = profileEmail;
    }
}
