package jihedamine.notesapp.appdirect.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventOrder {
    private String editionCode;

    @Override
    public String toString() {
        return "AppDirectOrder{" +
                "editionCode='" + editionCode + '\'' +
                '}';
    }

    public String getEditionCode() {
        return editionCode;
    }

}
