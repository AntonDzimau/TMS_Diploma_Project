package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopMenuPage extends BasePage{

    private final By pageIdentifier = By.xpath("");

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }
    @Override
    protected By getPageIdentifier() {
        return pageIdentifier;
    }
}
