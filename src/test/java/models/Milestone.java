package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Data
public class Milestone {
    private String name;
    @SerializedName(value = "refs")
    @JsonProperty("refs")
    private String references;
    private String description;
    @SerializedName(value = "is_completed")
    @JsonProperty("is_completed")
    private boolean isCompleted;
    private int id;
    @SerializedName(value = "start_on")
    @JsonProperty("start_on")
    private int startOn;
    @SerializedName(value = "due_on")
    @JsonProperty("due_on")
    private int dueOn;
}
