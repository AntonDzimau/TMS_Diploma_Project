package steps;

import baseEntities.BaseStep;
import entities.ProjectsEntities;
import io.qameta.allure.Step;
import models.Project;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.projects.AddProjectPage;
import pages.projects.ListOfProjectsPage;
import pages.projects.ProjectDetailsPage;

public class AddProjectStep extends BaseStep {
    AddProjectPage addProjectPage;
    DashboardPage dashboardPage;
    ProjectsEntities projectsEntities;
    ListOfProjectsPage listOfProjectsPage;
    ProjectDetailsPage projectDetailsPage;

    public AddProjectStep(WebDriver driver) {
        super(driver);
        dashboardPage = new DashboardPage(driver);
        addProjectPage = new AddProjectPage(driver);
        listOfProjectsPage = new ListOfProjectsPage(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
        projectsEntities = new ProjectsEntities();

    }

    @Step
    public void openPageByUrl() {
        addProjectPage.openPageByUrl();
    }

    @Step
    public ListOfProjectsPage addFirstTypeProject() {
        openPageByUrl();
        addProjectPage.getNameInput().sendKeys(projectsEntities.firstTypeProject.getName());
        addProjectPage.getAnnouncementTextarea().sendKeys(projectsEntities.firstTypeProject.getAnnouncement());
        addProjectPage.getShowAnnouncementCheckBox().flag(projectsEntities.firstTypeProject.isShowAnnouncement());
        addProjectPage.getRadioButton().selectByValue(projectsEntities.firstTypeProject.getType() + "");
        addProjectPage.getAcceptButton().click();
        listOfProjectsPage.openPageByUrl();
        /** Вот эта проверка почему-то не работает, ПОЧЕМУ?!
         хочу, чтобы открывалась страница только тогда, когда она НЕ открыта!
         * */
/*        if (!listOfProjectsPage.isPageOpened()){
            listOfProjectsPage.openPageByUrl();
        }*/
        return new ListOfProjectsPage(driver);
    }

    @Step
    public ListOfProjectsPage addSecondTypeProject() {
        openPageByUrl();
        addProjectPage.getNameInput().sendKeys(projectsEntities.secondTypeProject.getName());
        addProjectPage.getAnnouncementTextarea().sendKeys(projectsEntities.secondTypeProject.getAnnouncement());
        addProjectPage.getShowAnnouncementCheckBox().flag(projectsEntities.secondTypeProject.isShowAnnouncement());
        addProjectPage.getRadioButton().selectByValue(projectsEntities.secondTypeProject.getType() + "");
        addProjectPage.getAcceptButton().click();
        listOfProjectsPage.openPageByUrl();
        setIdToProject(projectsEntities.secondTypeProject);
        return new ListOfProjectsPage(driver);
    }

    private void setIdToProject(Project targetProject) {
        System.out.println( listOfProjectsPage.getListOfProjects()
                .getRow(listOfProjectsPage.getListOfProjects().getListOfRows().size()-1)
                .getCell(1)
                .getLinkFromCell()
                .getAttribute("href")
                .substring(74));
        targetProject.setId(Integer.parseInt(
                listOfProjectsPage.getListOfProjects()
                        .getRow(1)
                        .getCell(1)
                        .getLinkFromCell()
                        .getAttribute("href")
                        .substring(74))
        );
        System.out.println("ID только что созданного объекта - " + projectsEntities.secondTypeProject.getId());
        listOfProjectsPage.openPageByUrl();
    }

/*
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

    public void chooseRadioButtonAddProjectByText(String value){
        addProjectPage.getRadioButton().selectByText(value);
}

   public void deleteProject(){
        dashboardPage.clickAdministrationLink();
        dashboardPage.clickAllProjectLink();
        dashboardPage.deleteProject();
        dashboardPage.deleteProjectCheckBox();
        dashboardPage.submitForDelete();
    }*/

}

