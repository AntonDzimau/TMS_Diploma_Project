package steps;

import baseEntities.BaseStep;
import entities.ProjectsEntities;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.projects.ListOfProjectsPage;


public class RemoveProjectStep extends BaseStep {
    ListOfProjectsPage listOfProjectsPage;
    ProjectsEntities projectsEntities;

    public RemoveProjectStep(WebDriver driver) {
        super(driver);
        listOfProjectsPage = new ListOfProjectsPage(driver);
        projectsEntities = new ProjectsEntities();
    }

    @Step
    public void openPageByUrl() {
        listOfProjectsPage.openPageByUrl();
    }

    @Step
    public ListOfProjectsPage removeProjectsByName(String nameOfProject) {
        openPageByUrl();
        for (int i = listOfProjectsPage.getListOfProjects().getListOfRows().size(); i > 1; i--) {
            listOfProjectsPage.getListOfProjects().getRow(nameOfProject).getCell(2).getLinkFromCell().click();
            listOfProjectsPage.getCheckboxForRemoval().setFlag();
            listOfProjectsPage.getAcceptRemovalButton().click();
        }
        return new ListOfProjectsPage(driver);
    }

    @Step
    public ListOfProjectsPage removeAllProjects() {
        openPageByUrl();
        for (int i = listOfProjectsPage.getListOfProjects().getListOfRows().size(); i > 1; i--) {
            listOfProjectsPage.getListOfProjects().getRow(1).getCell(2).getLinkFromCell().click();
            listOfProjectsPage.getCheckboxForRemoval().setFlag();
            listOfProjectsPage.getAcceptRemovalButton().click();
        }
        return new ListOfProjectsPage(driver);
    }
}
