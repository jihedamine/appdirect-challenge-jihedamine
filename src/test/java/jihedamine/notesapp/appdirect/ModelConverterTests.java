package jihedamine.notesapp.appdirect;

import jihedamine.notesapp.appdirect.model.Event;
import jihedamine.notesapp.appdirect.services.impl.ModelConverter;
import jihedamine.notesapp.model.Company;
import jihedamine.notesapp.model.Marketplace;
import jihedamine.notesapp.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Tests the model converter correctly converts AppDirect entities to their NotesApp counterparts
 *
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public class ModelConverterTests {

    private Event event;

    @Before
    public void init() throws IOException {
        Resource dummyEvent = new ClassPathResource("dummyOrder.xml");
        byte[] encoded = Files.readAllBytes(Paths.get(dummyEvent.getFile().getPath()));
        String dummyEventContent = new String(encoded, Charset.defaultCharset());
        event = JAXB.unmarshal(new StringReader(dummyEventContent), Event.class);
    }

    @Test
    public void testCreateMarketplaceFromEventMarketplace() {
        Marketplace marketplace = ModelConverter.createMarketplaceFromEventMarketplace(event.getMarketplace());
        assertEquals("Wrong marketplace base url", "https://acme.appdirect.com", marketplace.getBaseUrl());
        assertEquals("Wrong marketplace partner", "ACME", marketplace.getPartner());
    }

    @Test
    public void testCreateUserFromEventPerson() {
        User user = ModelConverter.createUserFromEventPerson(event.getCreator());
        assertEquals("Wrong creator firstname", "DummyCreatorFirst", user.getFirstName());
        assertEquals("Wrong creator lastname", "DummyCreatorLast", user.getLastName());
        assertEquals("Wrong creator email", "test-email+creator@appdirect.com", user.getEmail());
        assertEquals("Wrong creator openid", "https://www.appdirect.com/openid/id/ec5d8eda-5cec-444d-9e30-125b6e4b67e2", user.getOpenId());
    }

    @Test
    public void testCreateCompanyFromEventCompany() {
        Company company = ModelConverter.createCompanyFromEventCompany(event.getPayload().getCompany());
        assertEquals("Wrong company name", "Example Company Name", company.getName());
        assertEquals("Wrong company email", "company-email@example.com", company.getEmail());
        assertEquals("Wrong company phone number", "415-555-1212", company.getPhoneNumber());
        assertEquals("Wrong company uuid", "d15bb36e-5fb5-11e0-8c3c-00262d2cda03", company.getUuid());
        assertEquals("Wrong company website", "http://www.example.com", company.getWebsite());
    }
}
