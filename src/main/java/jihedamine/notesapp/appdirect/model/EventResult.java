package jihedamine.notesapp.appdirect.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventResult {
    private Boolean success;
    private String accountIdentifier;
    private String errorCode;
    private String message;

    public EventResult() {
    }

    public static EventResult success() {
        EventResult result = new EventResult();
        result.success = true;
        return result;
    }

    public static EventResult success(String accountIdentifier, String message) {
        EventResult result = new EventResult();
        result.success = true;
        result.accountIdentifier = accountIdentifier;
        result.message = message;
        return result;
    }

    public static EventResult error(APIErrorCode apiErrorCode) {
        EventResult result = new EventResult();
        result.success = false;
        result.errorCode = apiErrorCode.name();
        result.message = apiErrorCode.getMessage();
        return result;
    }

    @Override
    public String toString() {
        return "EventResult{" +
                "success=" + success +
                ", accountIdentifier='" + accountIdentifier + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


}
