package jihedamine.notesapp.appdirect.services.impl;

import jihedamine.notesapp.appdirect.model.AttributeEntry;
import jihedamine.notesapp.appdirect.model.EventCompany;
import jihedamine.notesapp.appdirect.model.EventMarketplace;
import jihedamine.notesapp.appdirect.model.EventPerson;
import jihedamine.notesapp.model.Company;
import jihedamine.notesapp.model.Marketplace;
import jihedamine.notesapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to convert entities
 * from AppDirect Event model to NotesApp Event model
 *
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public class ModelConverter {

    public static User createUserFromEventPerson(EventPerson person) {
        User user = new User(person.getOpenId());
        user.setFirstName(person.getFirstName());
        user.setLastName(person.getLastName());
        user.setEmail(person.getEmail());
        user.setAppAdmin(false);

        // Get the user attribute to determine if the user is an admin
        if (person.getAttributes() != null) {
            for (AttributeEntry entry : person.getAttributes().getEntries()) {
                if (entry.getKey().equals("appAdmin")) {
                    user.setAppAdmin(Boolean.valueOf(entry.getValue()));
                    break;
                }
            }
        }

        return user;
    }

    public static Company createCompanyFromEventCompany(EventCompany eventCompany) {
        Company company = new Company();
        company.setUuid(eventCompany.getUuid());
        company.setName(eventCompany.getName());
        company.setEmail(eventCompany.getEmail());
        company.setPhoneNumber(eventCompany.getPhoneNumber());
        company.setWebsite(eventCompany.getWebsite());
        return company;
    }

    public static Marketplace createMarketplaceFromEventMarketplace(EventMarketplace eventMarketplace) {
        Marketplace marketplace = new Marketplace();
        marketplace.setPartner(eventMarketplace.getPartner());
        marketplace.setBaseUrl(eventMarketplace.getBaseUrl());
        return marketplace;
    }
}
