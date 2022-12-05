package pages;

import BaseEntities.BasePage;
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

    private final By successfulTextLocatorDeleteProject = By.className("message-success");

    private final By successfulTextLocatorAddProject=By.className("empty-title");

    private final By administrationLink = By.id("navigation-admin");

    private final By allProjectlinkLocator = By.id("navigation-sub-projects");

    private final By deleteProjectLink = By.className("icon-small-delete");

    private final By deleteCheckBox = By.name("deleteCheckbox");

    private final By submitDelete = By.linkText("OK");

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
        return waitsService.waitForVisibilityBy(administrationLink);
    }

    public void clickAdministrationLink() {
        getAdministrationLink().click();
    }

    public WebElement getAllProjectLink() {
        return waitsService.waitForVisibilityBy(allProjectlinkLocator);
    }

    public void clickAllProjectlink() {
        getAllProjectLink().click();
    }

    public WebElement getNavigationUserLink() {
        return waitsService.waitForVisibilityBy(navigationUserLinkLocator);
    }

    public Button addProject() {
        return new Button(driver, waitsService.waitForVisibilityBy(addProjectLocator));
    }

    public CheckBox deleteProjectCheckBox() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(deleteCheckBox));
    }

    public UIElement successfulText() {
        return new UIElement(driver, waitsService.waitForVisibilityBy(successfulTextLocatorDeleteProject));
    }
    public UIElement successfulTextAddProject(){
        return new UIElement(driver,waitsService.waitForVisibilityBy(successfulTextLocatorAddProject));
    }

    public WebElement getDeleteProjectLink() {
        return waitsService.waitForVisibilityBy(deleteProjectLink);
    }

    public void deleteProject() {
        getDeleteProjectLink().click();
    }

    public CheckBox getSubmitDeleteLink() {
        return new CheckBox(driver,waitsService.waitForVisibilityBy(submitDelete));
    }

    public void submitForDelete() {
        getSubmitDeleteLink().setFlag();
    }

}
