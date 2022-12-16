package utils;

public class Endpoints {
    public static final String GET_ALL_USERS = "index.php?/api/v2/get_users";
    public static final String GET_ALL_PROJECTS = "index.php?/api/v2/get_projects";
    public static final String ADD_MILESTONE = "index.php?/api/v2/add_milestone/{project_id}";
    public static final String GET_MILESTONES = "index.php?/api/v2/get_milestones/{project_id}";
    public static final String DELETE_MILESTONE = "index.php?/api/v2/delete_milestone/{milestone_id}";
    public static final String ADD_TESTCASE = "index.php?/api/v2/add_case/{section_id}";
    public static final String ADD_PROJECT = "index.php?/api/v2/add_project";
    public static final String UPDATE_PROJECT = "index.php?/api/v2/update_project/{project_id}";
}
