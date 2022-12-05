package entities;

import models.Milestone;


public class MilestoneEntities {
    public Milestone firstMilestone = Milestone.builder()
            .name("First Completed Milestone")
            .references("Some reference")
            .description("Description of completed Milestone 1")
            .isCompleted(true)
            .build();

    public Milestone secondMilestone = Milestone.builder()
            .name("Second Incompleted Milestone")
            .references("Some reference")
            .description("Description of incompleted Milestone 2")
            .isCompleted(false)
            .build();
}
