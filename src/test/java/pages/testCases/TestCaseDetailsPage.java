package pages.testCases;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCaseDetailsPage extends BasePage {
    private final static String pagePath = "/index.php?/cases/view/%1$d";
    private final By contentHeaderIdLocator = By.cssSelector(".content-header-id");
    private final By successfulTextLocator = By.className("message-success");

    public TestCaseDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return contentHeaderIdLocator;
    }

    public void openPageByUrl(int testCaseId) {
        super.openPageByUrl(String.format(pagePath, testCaseId));
    }

    public WebElement getSuccessfulText() {
        return driver.findElement(successfulTextLocator);
    }
}
