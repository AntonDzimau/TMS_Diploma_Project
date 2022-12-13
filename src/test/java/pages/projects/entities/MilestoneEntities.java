package pages.projects.entities;

import models.Milestone;


public class MilestoneEntities {
    static public Milestone completedMilestone = Milestone.builder()
            .name("First Completed Milestone")
            .references("Some reference")
            .description("Description of completed Milestone 1")
            .isCompleted(true)
            .build();

    static public Milestone completedMilestoneWithHugeName = Milestone.builder()
            .name("This name is H"+"U".repeat(236)+"GE!")
            .references("Some reference")
            .description("Description of completed Milestone 1")
            .isCompleted(true)
            .build();

    static public Milestone activeMilestone = Milestone.builder()
            .name("Second Incompleted Milestone")
            .references("Some reference")
            .description("Description of incompleted Milestone 2")
            .isCompleted(false)
            .build();
}
