package jihedamine.notesapp.appdirect.services.impl;

import jihedamine.notesapp.appdirect.exceptions.AppDirectEventException;
import jihedamine.notesapp.appdirect.model.APIErrorCode;
import jihedamine.notesapp.appdirect.model.Event;
import jihedamine.notesapp.appdirect.model.EventType;
import jihedamine.notesapp.appdirect.services.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private OAuthRestTemplate oAuthRestTemplate;

    public Event getEventFromUrl(String url, EventType eventType) throws AppDirectEventException, RestClientException {
        Event response = oAuthRestTemplate.getForEntity(url, Event.class).getBody();

        if (!response.getType().equals(eventType.name())) {
            throw new AppDirectEventException(APIErrorCode.UNKNOWN_ERROR);
        }

        return response;
    }
}
