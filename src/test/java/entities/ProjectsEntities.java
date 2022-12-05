package entities;

import models.Project;


public class ProjectsEntities {
    public Project firstTypeProject = Project.builder()
            .name("Test Project Name 1")
            .announcement("This is a description of the Test project 1.")
            .showAnnouncement(false)
            .type("1")
            .build();

    public Project secondTypeProject = Project.builder()
            .name("Test Project Name 2")
            .announcement("This is a description of the Test project 2.")
            .showAnnouncement(true)
            .type("2")
            .build();

    public Project thirdTypeProject = Project.builder()
            .name("Test Project Name 3")
            .announcement("This is a description of the Test project 3.")
            .showAnnouncement(true)
            .type("3")
            .build();
}
