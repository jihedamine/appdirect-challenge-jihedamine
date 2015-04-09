package jihedamine.notesapp.appdirect.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@XmlRootElement(name="marketplace")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventMarketplace {
    private String baseUrl;
    private String partner;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPartner() {
        return partner;
    }
}
