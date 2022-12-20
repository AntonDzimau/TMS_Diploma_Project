package pages;

import baseEntities.BasePage;
import elements.Button;
import elements.CheckBox;
import elements.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {
    private final static String pagePath = "index.php?/dashboard";

    private final By headerLogoLocator = By.cssSelector(".top-section");
    private final By navigationUserLinkLocator = By.id("navigation-user");
    private final By addProjectLocator = By.id("sidebar-projects-add");
    private final By successfulTextLocatorDeleteProjectLocator = By.className("message-success");
    private final By successfulTextLocatorAddProjectLocator = By.className("empty-title");
    private final By administrationLinkLocator = By.id("navigation-admin");
    private final By allProjectLinkLocator = By.id("navigation-sub-projects");
    private final By deleteProjectLinkLocator = By.className("icon-small-delete");
    private final By deleteCheckBoxLocator = By.name("deleteCheckbox");
    private final By submitDeleteLocator = By.linkText("OK");

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

    public WebElement getAdministrationLink() {
        return waitsService.waitForVisibilityBy(administrationLinkLocator);
    }

    public void clickAdministrationLink() {
        getAdministrationLink().click();
    }

    public WebElement getAllProjectLink() {
        return waitsService.waitForVisibilityBy(allProjectLinkLocator);
    }

    public void clickAllProjectLink() {
        getAllProjectLink().click();
    }

    public WebElement getNavigationUserLink() {
        return waitsService.waitForVisibilityBy(navigationUserLinkLocator);
    }

    public Button getAddProjectButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(addProjectLocator));
    }

    public CheckBox getDeleteProjectCheckBox() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(deleteCheckBoxLocator));
    }

    public UIElement getSuccessfulText() {
        return new UIElement(driver, waitsService.waitForVisibilityBy(successfulTextLocatorDeleteProjectLocator));
    }

    public UIElement getSuccessfulTextAfterAddingProject() {
        return new UIElement(driver, waitsService.waitForVisibilityBy(successfulTextLocatorAddProjectLocator));
    }

    public WebElement getLinkForDeleteProject() {
        return waitsService.waitForVisibilityBy(deleteProjectLinkLocator);
    }

    public void deleteProject() {
        getLinkForDeleteProject().click();
    }

    public CheckBox getSubmitDeleteLink() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(submitDeleteLocator));
    }

    public void submitForDeleting() {
        getSubmitDeleteLink().setFlag();
    }
}
