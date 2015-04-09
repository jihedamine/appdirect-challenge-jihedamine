package jihedamine.notesapp.appdirect.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@XmlRootElement(name = "payload")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payload {
    private EventAccount account;
    private EventCompany company;
    private EventPerson user;
    private EventOrder order;

    public EventAccount getAccount() {
        return account;
    }

    public EventCompany getCompany() {
        return company;
    }

    public EventOrder getOrder() {
        return order;
    }

    public EventPerson getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "account=" + account +
                ", company=" + company +
                ", user=" + user +
                ", order=" + order +
                '}';
    }

}
