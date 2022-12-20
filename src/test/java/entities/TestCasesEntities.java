package entities;



import models.TestCases;

public class TestCasesEntities {
    public final static TestCases firstTestcases = TestCases.builder()
            .name("Some name")
            .steps("Some name")
            .preconditions("Some name ")
            .expectedResult("Some name ")
            .build();


    static public TestCases testCasesForApiTest = TestCases.builder()
            .title("Title For Api")
            .type(6)
            .priority(1)
            .references("RFC-1, RFC-2")
            .build();
}
