package pages.projects.entities;

import models.Project;

import java.util.Objects;


public class ProjectsEntities {
    public final static Project firstTypeProject = Project.builder()
            .name("Test Project Name 1")
            .announcement("This is a description of the Test project 1.")
            .showAnnouncement(false)
            .type("1")
            .build();

    public final static Project secondTypeProject = Project.builder()
            .name("Test Project Name 2")
            .announcement("This is a description of the Test project 2.")
            .showAnnouncement(true)
            .type("2")
            .build();

    public final static Project thirdTypeProject = Project.builder()
            .name("Test Project Name 3")
            .announcement("This is a description of the Test project 3.")
            .showAnnouncement(true)
            .type("3")
            .build();

    public static Project getProject(String projectName) {
        if (Objects.equals(secondTypeProject.getName(), projectName)) {
            return secondTypeProject;
        } else if (Objects.equals(firstTypeProject.getName(), projectName)) {
            return firstTypeProject;
        } else if (Objects.equals(thirdTypeProject.getName(), projectName)) {
            return thirdTypeProject;
        } else {
            return null;
        }
    }
}
