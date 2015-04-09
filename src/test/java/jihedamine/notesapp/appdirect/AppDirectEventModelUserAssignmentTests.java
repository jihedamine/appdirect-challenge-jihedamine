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
 * are in accordance with the XML events sent by AppDirect for user assignment.
 *
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public class AppDirectEventModelUserAssignmentTests {

    private Event event;

    @Before
    public void init() throws IOException {
        Resource dummyEvent = new ClassPathResource("dummyAssign.xml");
        byte[] encoded = Files.readAllBytes(Paths.get(dummyEvent.getFile().getPath()));
        String dummyEventContent = new String(encoded, Charset.defaultCharset());
        event = JAXB.unmarshal(new StringReader(dummyEventContent), Event.class);
    }

    @Test
    public void testEventType() {
        assertEquals("Wrong event type", EventType.USER_ASSIGNMENT, EventType.valueOf(event.getType()));
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
    public void testPayloadAccount() {
        EventAccount account = event.getPayload().getAccount();
        assertEquals("Wrong account identifier", "dummy-account", account.getAccountIdentifier());
        assertEquals("Wrong account status", "ACTIVE", account.getStatus());
    }

    @Test
    public void testPayloadUser() {
        EventPerson user = event.getPayload().getUser();
        assertEquals("Wrong user firstname", "DummyFirst", user.getFirstName());
        assertEquals("Wrong user lastname", "DummyLast", user.getLastName());
        assertEquals("Wrong user openid", "https://www.appdirect.com/openid/id/ec5d8eda-5cec-444d-9e30-125b6e4b67e2", user.getOpenId());
        assertEquals("Wrong user uuid", "ec5d8eda-5cec-444d-9e30-125b6e4b67e2", user.getUuid());
        assertEquals("Wrong user email", "test-email@appdirect.com", user.getEmail());
        assertEquals("Wrong user language", "fr", user.getLanguage());

        AttributeEntry favoriteColor = user.getAttributes().getEntries().get(0);
        assertEquals("Wrong attribute key", "favoriteColor", favoriteColor.getKey());
        assertEquals("Wrong attribute value", "green", favoriteColor.getValue());

        AttributeEntry hourlyRate = user.getAttributes().getEntries().get(1);
        assertEquals("Wrong attribute key", "hourlyRate", hourlyRate.getKey());
        assertEquals("Wrong attribute value", "40", hourlyRate.getValue());
    }


}
