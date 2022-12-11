package pages.projects;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddProjectPage extends BaseProjectPage{
    private final static String pagePath = "index.php?/admin/projects/add";
    private final By acceptButtonLocator = By.xpath("//button[contains(.,'Add Project')]");
    private final By helpLinkFormattingLocator = By.xpath("//a[@class='link-tooltip'][2]");

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return acceptButtonLocator;
    }

    public void openPageByUrl() {
        super.openPageByUrl(pagePath);
    }

    public Button getAcceptButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(acceptButtonLocator));
    }

    public WebElement getHelpLinkFormatting() {
        return waitsService.waitForVisibilityBy(helpLinkFormattingLocator);
    }

}
