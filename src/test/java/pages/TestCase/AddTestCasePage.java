package pages.TestCase;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddTestCasePage extends BaseTestCasePage {

    private final By pageIdentifier = By.className("page_title");

    private final By acceptButtonLocator = By.id("accept");


    public AddTestCasePage(WebDriver driver) {
        super(driver);
    }


    @Override
    protected By getPageIdentifier() {
        return pageIdentifier;
    }

    public Button getAcceptButtonLocator() {
        return new Button(driver, waitsService.waitForVisibilityBy(acceptButtonLocator));
    }
}
