package jihedamine.notesapp.appdirect.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@XmlRootElement(name="event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event
{
    private String type;
    private EventMarketplace marketplace;

    @XmlElement(name="creator")
    private EventPerson creator;

    private Payload payload;

    public String getType() {
        return type;
    }

    public EventMarketplace getMarketplace() {
        return marketplace;
    }

    public EventPerson getCreator() {
        return creator;
    }

    public Payload getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", marketplace=" + marketplace +
                ", creator=" + creator +
                ", payload=" + payload +
                '}';
    }
}