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
    @FindBy(id ="register")
    private Link register;

    public void openIndexPage(){
        getIndex().click();
    }
    public Link getRegister() {
        return register;
    }

    public void setRegister(Link register) {
        this.register = register;
    }

    public Link getIndex() {
        return index;
    }

    public void setIndex(Link index) {
        this.index = index;
    }
}
