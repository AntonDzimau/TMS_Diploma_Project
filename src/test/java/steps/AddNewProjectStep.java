package steps;

import BaseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.projects.AddProjectPage;

public class AddNewProjectStep extends BaseStep {

    DashboardPage dashboardPage;

    AddProjectPage addProjectPage;

    public AddNewProjectStep(WebDriver driver) {
        super(driver);
        dashboardPage = new DashboardPage(driver);
        addProjectPage = new AddProjectPage(driver);
    }

    public void addNewProject() {
        dashboardPage.addProject().click();
    }

    public void saveProject() {
        addProjectPage.getSaveButton().click();
    }

    public void setNameProject(String value) {
        addProjectPage.getNameInput().sendKeys(value);
    }

    public void seTCheckBox() {
        addProjectPage.getShowAnnouncement().setFlag();
    }

    public void removeCheckBox() {
        addProjectPage.getShowAnnouncement().removeFlag();
    }

    public String getSuccessfulTextForDeleteProject() {
        return dashboardPage.successfulText().getText();
    }


    public String getSuccessfulTextForAddProject(){
        return dashboardPage.successfulTextAddProject().getText();
    }
    public String getErrorText(){
        return addProjectPage.ErrorText().getText();
    }
    public void chooseRadioButtonAddProjectByValue(String value){
        addProjectPage.getRadioButton().selectByValue(value);
    }
    public void chooseRadioButtonAddProjectBytext(String value){
        addProjectPage.getRadioButton().selectByText(value);
}
   public void deleteProject(){
        dashboardPage.clickAdministrationLink();
        dashboardPage.clickAllProjectlink();
        dashboardPage.deleteProject();
        dashboardPage.deleteProjectCheckBox();
        dashboardPage.submitForDelete();
    }

}

