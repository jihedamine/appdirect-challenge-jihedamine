package jihedamine.notesapp.appdirect.services.impl;

import jihedamine.notesapp.appdirect.exceptions.AppDirectEventException;
import jihedamine.notesapp.appdirect.model.*;
import jihedamine.notesapp.appdirect.services.AppDirectService;
import jihedamine.notesapp.model.*;
import jihedamine.notesapp.repositories.CompanyAccountRepository;
import jihedamine.notesapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.UUID;

/**
 * Service handling AppDirect events
 *
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Service
public class AppDirectServiceImpl implements AppDirectService {

    private static Logger log = LoggerFactory.getLogger(AppDirectServiceImpl.class);

    @Autowired
    CompanyAccountRepository companyAccountRepository;

    @Autowired
    UserRepository userRepository;

    public EventResult createSubscription(Event event) throws AppDirectEventException, RestClientException {
        EventCompany eventCompany = event.getPayload().getCompany();
        EventOrder order = event.getPayload().getOrder();
        EventPerson orderCreator = event.getCreator();
        EventMarketplace eventMarketplace = event.getMarketplace();

        Company company = ModelConverter.createCompanyFromEventCompany(eventCompany);
        Marketplace marketplace = ModelConverter.createMarketplaceFromEventMarketplace(eventMarketplace);
        CompanyAccount companyAccount = new CompanyAccount(UUID.randomUUID().toString(), EditionCode.valueOf(order.getEditionCode()), company);
        companyAccount.setMarketplace(marketplace);

        User user = ModelConverter.createUserFromEventPerson(orderCreator);
        // By default, set the subscription creator as an app admin
        user.setAppAdmin(true);
        // Assign the order creator to the company
        companyAccount.assignUser(user);

        companyAccount = companyAccountRepository.save(companyAccount);

        // Bidirectional relation
        user.setCompanyAccount(companyAccount);
        userRepository.save(user);

        log.info("Subscription created successfully for account: " + companyAccount.getAccountIdentifier());

        return EventResult.success(companyAccount.getAccountIdentifier(), "Account creation successful");
    }

    public EventResult changeSubscription(Event event) throws AppDirectEventException, RestClientException {
        String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();
        CompanyAccount companyAccount = companyAccountRepository.findByAccountIdentifier(accountIdentifier);

        if (companyAccount == null) {
            throw new AppDirectEventException(APIErrorCode.ACCOUNT_NOT_FOUND);
        }

        EditionCode newEditionCode = EditionCode.valueOf(event.getPayload().getOrder().getEditionCode());
        companyAccount.setEditionCode(newEditionCode);
        companyAccount.setStatus(CompanyAccount.getInitialStatus(newEditionCode));

        companyAccountRepository.save(companyAccount);

        log.info("Subscription changed successfully to " + newEditionCode + " for account: " + companyAccount.getAccountIdentifier());

        return EventResult.success(accountIdentifier, "Subscription change successful");
    }

    public EventResult cancelSubscription(Event event) throws AppDirectEventException, RestClientException {
        String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();
        CompanyAccount companyAccount = companyAccountRepository.findByAccountIdentifier(accountIdentifier);

        if (companyAccount == null) {
            throw new AppDirectEventException(APIErrorCode.ACCOUNT_NOT_FOUND);
        }

        companyAccountRepository.delete(companyAccount);

        log.info("Subscription cancelled successfully for account " + accountIdentifier);

        return EventResult.success(accountIdentifier, "Subscription cancellation successful");
    }

    public EventResult noticeSubscription(Event event) throws AppDirectEventException, RestClientException {
        String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();
        CompanyAccount companyAccount = companyAccountRepository.findByAccountIdentifier(accountIdentifier);

        if (companyAccount == null) {
            throw new AppDirectEventException(APIErrorCode.ACCOUNT_NOT_FOUND);
        }

        AccountStatus status = AccountStatus.valueOf(event.getPayload().getAccount().getStatus());

        if (status.equals(AccountStatus.CANCELLED)) {
            companyAccountRepository.delete(companyAccount);
        } else {
            companyAccount.setStatus(status);
            companyAccountRepository.save(companyAccount);
        }

        log.info("Subscription notice handled successfully for account " + accountIdentifier);

        return EventResult.success(accountIdentifier, "Subscription notice handled successful");
    }

    public EventResult assignUser(Event event) throws AppDirectEventException, RestClientException {
        String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();

        CompanyAccount companyAccount = companyAccountRepository.findByAccountIdentifier(accountIdentifier);

        if (companyAccount == null) {
            throw new AppDirectEventException(APIErrorCode.ACCOUNT_NOT_FOUND);
        }

        EventPerson eventUser = event.getPayload().getUser();
        User user = userRepository.findByOpenId(eventUser.getOpenId());

        if (user == null) {
            user = ModelConverter.createUserFromEventPerson(eventUser);
        } else if (companyAccount.getUsers().contains(user)) {
            throw new AppDirectEventException(APIErrorCode.USER_ALREADY_EXISTS);
        }

        companyAccount.assignUser(user);
        companyAccountRepository.save(companyAccount);

        log.info("User with openid " + eventUser.getOpenId() + "assigned successfully to account " + accountIdentifier);

        return EventResult.success();
    }

    public EventResult unassignUser(Event event) throws AppDirectEventException, RestClientException {
        String accountIdentifier = event.getPayload().getAccount().getAccountIdentifier();
        CompanyAccount companyAccount = companyAccountRepository.findByAccountIdentifier(accountIdentifier);

        if (companyAccount == null) {
            throw new AppDirectEventException(APIErrorCode.ACCOUNT_NOT_FOUND);
        }

        EventPerson eventUser = event.getPayload().getUser();
        User user = userRepository.findByOpenId(eventUser.getOpenId());

        if (user == null) {
            throw new AppDirectEventException(APIErrorCode.USER_NOT_FOUND);
        }

        // Triggers user deletion as orphan removal is true for company-users relation
        companyAccount.unassignUser(user);

        companyAccountRepository.save(companyAccount);

        log.info("User with openid " + eventUser.getOpenId() + "assigned successfully to account " + accountIdentifier);

        return EventResult.success();
    }



}
