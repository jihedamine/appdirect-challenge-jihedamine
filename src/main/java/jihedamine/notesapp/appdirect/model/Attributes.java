package jihedamine.notesapp.appdirect.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@XmlRootElement(name="attributes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Attributes {

    private List<AttributeEntry> entry;

    public List<AttributeEntry> getEntries() {
        return entry;
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "entries=" + entry +
                '}';
    }
}
