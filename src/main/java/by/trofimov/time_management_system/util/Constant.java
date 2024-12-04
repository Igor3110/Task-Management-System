package by.trofimov.time_management_system.util;

import lombok.experimental.UtilityClass;

/**
 * Utility class with application constants.
 */
@UtilityClass
public class Constant {

    /**
     * Text constants.
     */
    public static final int MIN_TEXT_FIELD_ROLE_LENGTH = 6;
    public static final int MAX_TEXT_FIELD_ROLE_LENGTH = 64;
    public static final String MESSAGE_FOR_INCORRECT_ROLE_NAME_TEXT_FIELD =
            "Field should be more than " + MIN_TEXT_FIELD_ROLE_LENGTH +
                    " symbols and less then " + MAX_TEXT_FIELD_ROLE_LENGTH + " symbols";

    public static final int MIN_TEXT_FIELD_LENGTH = 2;
    public static final int MAX_TEXT_FIELD_LENGTH = 64;
    public static final String MESSAGE_FOR_INCORRECT_TEXT_FIELD =
            "Field should be more than " + MIN_TEXT_FIELD_LENGTH +
                    " symbols and less then " + MAX_TEXT_FIELD_LENGTH + " symbols";

    public static final int MIN_TEXT_FIELD_LENGTH_FOR_COMMENT = 2;
    public static final int MAX_TEXT_FIELD_LENGTH_FOR_COMMENT = 512;
    public static final String MESSAGE_FOR_INCORRECT_COMMENT_TEXT_FIELD =
            "Field should be more than " + MIN_TEXT_FIELD_LENGTH_FOR_COMMENT
                    + " symbols and less then " + MAX_TEXT_FIELD_LENGTH_FOR_COMMENT + " symbols";

    public static final int MIN_PASSWORD_FIELD_LENGTH = 4;
    public static final int MAX_PASSWORD_FIELD_LENGTH = 255;
    public static final String MESSAGE_FOR_INCORRECT_PASSWORD =
            "Password field should be more than " + MIN_PASSWORD_FIELD_LENGTH +
                    " symbols and less then " + MAX_PASSWORD_FIELD_LENGTH + " symbols";

    public static final String INCORRECT_EMAIL = "Enter a correct email";
    public static final String ROLES = "roles";
    public static final String BEARER_ = "Bearer ";
    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_NOT_FOUND_WITH_ID =
            "Role not found. Role id: %s";
    public static final String ROLE_NOT_FOUND_WITH_NAME =
            "Role not found. Role name: %s";

    public static final String USER_NOT_FOUND_WITH_ID =
            "User not found. User id: %s";
    public static final String USER_NOT_FOUND_WITH_USERNAME =
            "User not found. Username: %s";
    public static final String USER_NOT_FOUND_WITH_EMAIL =
            "User not found. User email: %s";

    public static final String COMMENT_NOT_FOUND_WITH_ID =
            "Comment not found. Comment id: %s";

    public static final String TASK_NOT_FOUND_WITH_ID =
            "Task not found. Task id: %s";

    public static final String TASK_STATUS_NOT_FOUND =
            "Such task status not found";

    public static final String ROLE_WITH_SAME_NAME_EXISTS =
            "A role with the same name already exists";
    public static final String USER_WITH_SAME_NAME_OR_EMAIL_EXISTS =
            "A user with the same name or email already exists";

    public static final String ENTITY_WAS_SUCCESSFULLY_DELETED =
            "Entity with id: %s was successfully deleted";
    public static final String COMMENT_WITH_ID_WAS_SUCCESSFULLY_DELETED =
            "Comment with id: %s was successfully deleted";
    public static final String EXECUTOR_FOR_TASK_WITH_ID_WAS_SUCCESSFULLY_DELETED =
            "Executor with id: %s for Task with id: %s was successfully deleted";

}
