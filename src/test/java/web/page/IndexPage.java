package web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * autor:maksim
 * date: 19.05.15
 * time: 17:44.
 */
public class IndexPage extends AbstractPage{
    public IndexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver),this);
    }
}
