package entities;


import models.TestCases;

public class TestCasesEntities {
    public final static TestCases firstTestcases = TestCases.builder()
            .name("Some name")
            .steps("Some name")
            .preconditions("Some name ")
            .expectedResult("Some name ")
            .build();
}
