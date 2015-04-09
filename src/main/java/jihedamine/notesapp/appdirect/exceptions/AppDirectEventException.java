package jihedamine.notesapp.appdirect.exceptions;

import jihedamine.notesapp.appdirect.model.APIErrorCode;

/**
 * Throws exceptions related to AppDirect's API error codes
 * @see <a href="http://info.appdirect.com/developers/docs/event_references/api_error_codes">Error Codes</a>
 *
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public class AppDirectEventException extends Exception {
    private APIErrorCode errorCode;

    public AppDirectEventException(APIErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public APIErrorCode getErrorCode() {
        return errorCode;
    }
}
