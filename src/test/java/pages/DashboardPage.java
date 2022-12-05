package pages;

import BaseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {
    private final static String pagePath = "index.php?/dashboard";
    private final By headerLogoLocator = By.cssSelector(".top-section");
    private final By navigationUserLinkLocator = By.id("navigation-user");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }


    @Override
    protected By getPageIdentifier() {
        return navigationUserLinkLocator;
    }

    public void openPageByUrl() {
        super.openPageByUrl(pagePath);
    }

    public WebElement getHeaderLogo() {
        return waitsService.waitForVisibilityBy(headerLogoLocator);
    }

    public WebElement getNavigationUserLink() {
        return waitsService.waitForVisibilityBy(navigationUserLinkLocator);
    }

}
