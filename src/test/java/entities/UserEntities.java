package entities;

import models.TestCases;
import models.User;

public class UserEntities {
    static public User UserForApiTest = User.builder()
            .id(1)
            .name("Some Students")
            .email("chehov.anton96@gmail.com")
            .role("Lead")
            .isAdmin(true)
            .build();
}
