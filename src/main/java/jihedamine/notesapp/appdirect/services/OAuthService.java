package jihedamine.notesapp.appdirect.services;

import jihedamine.notesapp.appdirect.exceptions.AppDirectEventException;
import jihedamine.notesapp.appdirect.model.Event;
import jihedamine.notesapp.appdirect.model.EventType;
import org.springframework.web.client.RestClientException;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public interface OAuthService {

    /**
     * Get an event from an OAuth signed request sent by AppDirect
     *
     * @param url url of the request sent by AppDirect
     * @param eventType type of event we want to retrieve
     * @return event retrieved from the url
     * @throws AppDirectEventException
     * @throws RestClientException
     */
    public Event getEventFromUrl(String url, EventType eventType) throws AppDirectEventException, RestClientException;
}
