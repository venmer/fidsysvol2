package web.page;

import web.elements.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * autor:maksim
 * date: 19.05.15
 * time: 17:46.
 */
public abstract class AbstractPage {
    protected WebDriver webDriver;
    protected String baseUrl="http://localhost:8081";

    @FindBy(className = "navbar-main")
    private Main main;

    public Main getMainMenu() {
        return this.main;
    }

    public AbstractPage(final WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public IndexPage  openIndexPage() {
        getMainMenu().openIndexPage();
        return new IndexPage(webDriver);
    }
}
