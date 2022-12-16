package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@Builder
@ToString
@Getter
@Setter

public class TestCases {
    @JsonProperty("type_id")
    @SerializedName(value = "type_id")
    private int type;
    private String title;
    @JsonProperty("priority_id")
    @SerializedName(value = "priority_id")
    private int priority;
    @JsonProperty("refs")
    @SerializedName(value = "refs")
    private String references;
    private String name;
    private String steps;
    private String preconditions;
    private String expectedResult;
    private int id;


}
