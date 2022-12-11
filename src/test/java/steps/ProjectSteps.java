package steps;

import baseEntities.BaseStep;
import pages.projects.entities.ProjectsEntities;
import io.qameta.allure.Step;
import models.Project;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.projects.AddProjectPage;
import pages.projects.ListOfProjectsPage;
import pages.projects.ProjectDetailsPage;

public class ProjectSteps extends BaseStep {
    AddProjectPage addProjectPage;
    DashboardPage dashboardPage;
    ListOfProjectsPage listOfProjectsPage;
    ProjectDetailsPage projectDetailsPage;

    public ProjectSteps(WebDriver driver) {
        super(driver);
        dashboardPage = new DashboardPage(driver);
        addProjectPage = new AddProjectPage(driver);
        listOfProjectsPage = new ListOfProjectsPage(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
    }

    @Step
    public void openPageByUrl() {
        addProjectPage.openPageByUrl();
    }

    @Step
    public ListOfProjectsPage addProject(Project targetProject) {
        openPageByUrl();
        addProjectPage.getNameInput().sendKeys(targetProject.getName());
        addProjectPage.getAnnouncementTextarea().sendKeys(targetProject.getAnnouncement());
        addProjectPage.getShowAnnouncementCheckBox().flag(targetProject.isShowAnnouncement());
        addProjectPage.getRadioButton().selectByValue(targetProject.getType());
        addProjectPage.getAcceptButton().click();
        listOfProjectsPage.openPageByUrl();
        setIdToProject(targetProject);
        System.out.println("After adding id is - " + ProjectsEntities.secondTypeProject.getId());
        /** Вот эта проверка почему-то не работает, ПОЧЕМУ?!
         хочу, чтобы открывалась страница только тогда, когда она НЕ открыта!
         * */
/*        if (!listOfProjectsPage.isPageOpened()){
            listOfProjectsPage.openPageByUrl();
        }*/
        return new ListOfProjectsPage(driver);
    }


    @Step
    public ListOfProjectsPage removeProjectsByName(String targetProject) {
        listOfProjectsPage.openPageByUrl();
        for (int i = listOfProjectsPage.getListOfProjects().getListOfRows().size(); i > 1; i--) {
            if (listOfProjectsPage.getListOfProjects().isFoundInTable(targetProject)) {
                listOfProjectsPage.getListOfProjects().getRow(targetProject).getCell(2).getLinkFromCell().click();
                listOfProjectsPage.getCheckboxForRemoval().setFlag();
                listOfProjectsPage.getAcceptRemovalButton().click();
            } else {
                break;
            }
        }
        return new ListOfProjectsPage(driver);
    }

    @Step
    public ListOfProjectsPage removeAllProjects() {
        listOfProjectsPage.openPageByUrl();
        for (int i = listOfProjectsPage.getListOfProjects().getListOfRows().size(); i > 1; i--) {
            listOfProjectsPage.getListOfProjects().getRow(1).getCell(2).getLinkFromCell().click();
            listOfProjectsPage.getCheckboxForRemoval().setFlag();
            listOfProjectsPage.getAcceptRemovalButton().click();
        }
        return new ListOfProjectsPage(driver);
    }

    private void setIdToProject(Project targetProject) {
        for (int i = listOfProjectsPage.getListOfProjects().getListOfRows().size(), j = 0; i > 1; i--) {
            j++;
            if (listOfProjectsPage.getListOfProjects().getListOfRows().get(i - 1).getCell(0).getLinkFromCell().getText().contains(targetProject.getName())) {
                System.out.println("Я нашел нужный проект! Его номер снизу - " + j);
                String link = listOfProjectsPage.getListOfProjects()
                        .getRow(listOfProjectsPage.getListOfProjects().getListOfRows().size() - j)
                        .getCell(0)
                        .getLinkFromCell()
                        .getAttribute("href");
                ProjectsEntities.getProject(targetProject.getName()).setId(
                        Integer.parseInt(
                                link.substring(74))
                );
                System.out.println("After setting id is - " + ProjectsEntities.secondTypeProject.getId());
                break;
            }
        }
    }
}

