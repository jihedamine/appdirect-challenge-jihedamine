package jihedamine.notesapp.appdirect.model;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public enum APIErrorCode {
    USER_ALREADY_EXISTS ("A user with this id already has the product"),
    USER_NOT_FOUND ("The user cannot be found"),
    ACCOUNT_NOT_FOUND ("The account can not be found"),
    MAX_USERS_REACHED ("Maximum users reached"),
    UNAUTHORIZED ("Unauthorized operation"),
    OPERATION_CANCELED ("Canceled operation"),
    CONFIGURATION_ERROR ("Configuration error"),
    INVALID_RESPONSE ("Invalid response"),
    UNKNOWN_ERROR ("Unknown error"),
    PENDING ("Pending operation");

    private String message;

    APIErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
