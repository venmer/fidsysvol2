package web.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

/**
 * autor:maksim
 * date: 19.05.15
 * time: 17:50.
 */
public class Main extends HtmlElement {
    @FindBy(id="index")
    private Link index;
    @FindBy(id = "profile")
    private Link profile;
    @FindBy(id = "try-demo")
    private Link demo;
    @FindBy(id = "user-profile")
    private Link userProfile;
    @FindBy(id = "user-signout")
    private Link userSignout;
    @FindBy(id = "user-register")
    private Link userRegister;
    @FindBy(id = "user-signin")
    private Link userSignin;
    @FindBy(id = "user-menu")
    private Link userMenu;

    public Link getUserMenu() {
        return userMenu;
    }

    public void setUserMenu(Link userMenu) {
        this.userMenu = userMenu;
    }

    public Link getIndex() {
        return index;
    }

    public void setIndex(Link index) {
        this.index = index;
    }

    public Link getDemo() {
        return demo;
    }

    public void setDemo(Link demo) {
        this.demo = demo;
    }

    public Link getProfile() {
        return profile;
    }

    public void setProfile(Link profile) {
        this.profile = profile;
    }

    public Link getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(Link userProfile) {
        this.userProfile = userProfile;
    }

    public Link getUserSignout() {
        return userSignout;
    }

    public void setUserSignout(Link userSignout) {
        this.userSignout = userSignout;
    }

    public Link getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(Link userRegister) {
        this.userRegister = userRegister;
    }

    public Link getUserSignin() {
        return userSignin;
    }

    public void setUserSignin(Link userSignin) {
        this.userSignin = userSignin;
    }

    public void openRegisterForm() {
        getUserRegister().click();
    }

    public void openIndexPage() {
        index.click();
    }
}
