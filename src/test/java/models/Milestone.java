package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class Milestone {
    private String name;
    private String references;
    private String description;
    private boolean isCompleted;
    private int id;
}
