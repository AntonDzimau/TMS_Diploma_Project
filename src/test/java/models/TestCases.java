package models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@Builder
@ToString
@Getter
@Setter

public class TestCases {
    @SerializedName(value = "type_id")
    private int type;
    private String title;
    @SerializedName(value = "priority_id")
    private int priority;
    @SerializedName(value = "refs")
    private String references;
    private String name;
    private String steps;
    private String preconditions;
    private String expectedResult;
    private int id;
}
