package jihedamine.notesapp.appdirect;

import jihedamine.notesapp.appdirect.model.*;
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
 * Assert that the event entities defined in jihedamine.notesapp.appdirect.model
 * are in accordance with the XML events sent by AppDirect for subscription ordering.
 *
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public class AppDirectEventModelOrderCreationTests {

    private Event event;

    @Before
    public void init() throws IOException {
        Resource dummyEvent = new ClassPathResource("dummyOrder.xml");
        byte[] encoded = Files.readAllBytes(Paths.get(dummyEvent.getFile().getPath()));
        String dummyEventContent = new String(encoded, Charset.defaultCharset());
        event = JAXB.unmarshal(new StringReader(dummyEventContent), Event.class);
    }

    @Test
    public void testEventType() {
        assertEquals("Wrong event type", EventType.SUBSCRIPTION_ORDER, EventType.valueOf(event.getType()));
    }

    @Test
    public void testEventMarketplace() {
        EventMarketplace marketplace = event.getMarketplace();
        assertEquals("Wrong marketplace base url", "https://acme.appdirect.com", marketplace.getBaseUrl());
        assertEquals("Wrong marketplace partner", "ACME", marketplace.getPartner());
    }

    @Test
    public void testEventCreator() {
        EventPerson creator = event.getCreator();
        assertEquals("Wrong creator firstname", "DummyCreatorFirst", creator.getFirstName());
        assertEquals("Wrong creator lastname", "DummyCreatorLast", creator.getLastName());
        assertEquals("Wrong creator email", "test-email+creator@appdirect.com", creator.getEmail());
        assertEquals("Wrong creator language", "fr", creator.getLanguage());
        assertEquals("Wrong creator openid", "https://www.appdirect.com/openid/id/ec5d8eda-5cec-444d-9e30-125b6e4b67e2", creator.getOpenId());
        assertEquals("Wrong creator uuid", "ec5d8eda-5cec-444d-9e30-125b6e4b67e2", creator.getUuid());
    }

    @Test
    public void testPayloadCompany() {
        EventCompany company = event.getPayload().getCompany();
        assertEquals("Wrong company name", "Example Company Name", company.getName());
        assertEquals("Wrong company email", "company-email@example.com", company.getEmail());
        assertEquals("Wrong company phone number", "415-555-1212", company.getPhoneNumber());
        assertEquals("Wrong company uuid", "d15bb36e-5fb5-11e0-8c3c-00262d2cda03", company.getUuid());
        assertEquals("Wrong company website", "http://www.example.com", company.getWebsite());
    }

    @Test
    public void testPayloadOrder() {
        EventOrder order = event.getPayload().getOrder();
        assertEquals("Wrong order edition code", "BASIC", order.getEditionCode());
    }

}
