package jihedamine.notesapp.appdirect.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventAccount {
    private String accountIdentifier;
    private String status;

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AppDirectAccount{" +
                "accountIdentifier='" + accountIdentifier + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
