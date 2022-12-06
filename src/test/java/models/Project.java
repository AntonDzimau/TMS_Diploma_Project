package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class Project {
    private String name;
    private String announcement;
    private String type;
    private boolean showAnnouncement;
    private boolean access;
    private boolean deleted;
    private int id;
}
