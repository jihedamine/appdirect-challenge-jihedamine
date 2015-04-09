package jihedamine.notesapp.appdirect.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventPerson {
    private String email;
    private String firstName;
    private String lastName;
    private String openId;
    private String uuid;
    private String language;

    private Attributes attributes;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOpenId() {
        return openId;
    }

    public String getUuid() {
        return uuid;
    }

    public String getLanguage() {
        return language;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "EventPerson{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", openId='" + openId + '\'' +
                ", uuid='" + uuid + '\'' +
                ", language='" + language + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}



