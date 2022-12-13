package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class TestCases {
    private String name;
    private String preconditions;
    private String steps;
    private String expectedResult;

}
