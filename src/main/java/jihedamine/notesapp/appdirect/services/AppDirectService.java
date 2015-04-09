package jihedamine.notesapp.appdirect.services;

import jihedamine.notesapp.appdirect.exceptions.AppDirectEventException;
import jihedamine.notesapp.appdirect.model.Event;
import jihedamine.notesapp.appdirect.model.EventResult;
import org.springframework.web.client.RestClientException;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public interface AppDirectService {

    /**
     * Create a new subscription to NotesApp
     *
     * @param event AppDirect event
     * @return response to the AppDirect notification
     * @throws AppDirectEventException if the event triggered an AppDirectErrorCode @see <a href="http://info.appdirect.com/developers/docs/event_references/api_error_codes">Error Codes</a>
     * @throws RestClientException if the event can't be processed
     */
    public EventResult createSubscription(Event event) throws AppDirectEventException, RestClientException;

    /**
     * Change an existing subscription to NotesApp
     *
     * @param event AppDirect event
     * @return response to the AppDirect notification
     * @throws AppDirectEventException if the event triggered an AppDirectErrorCode @see <a href="http://info.appdirect.com/developers/docs/event_references/api_error_codes">Error Codes</a>
     * @throws RestClientException if the event can't be processed
     */
    public EventResult changeSubscription(Event event) throws AppDirectEventException, RestClientException;

    /**
     * Cancel an existing subscription to NotesApp
     *
     * @param event AppDirect event
     * @return response to the AppDirect notification
     * @throws AppDirectEventException if the event triggered an AppDirectErrorCode @see <a href="http://info.appdirect.com/developers/docs/event_references/api_error_codes">Error Codes</a>
     * @throws RestClientException if the event can't be processed
     */
    EventResult cancelSubscription(Event event) throws AppDirectEventException, RestClientException;

    /**
     * Notice that the subscription status has changed
     *
     * @param event AppDirect event
     * @return response to the AppDirect notification
     * @throws AppDirectEventException if the event triggered an AppDirectErrorCode @see <a href="http://info.appdirect.com/developers/docs/event_references/api_error_codes">Error Codes</a>
     * @throws RestClientException if the event can't be processed
     */
     EventResult noticeSubscription(Event event) throws AppDirectEventException, RestClientException;

    /**
     * Assign an existing user to a NotesApp account
     *
     * @param event AppDirect event
     * @return response to the AppDirect notification
     * @throws AppDirectEventException if the event triggered an AppDirectErrorCode @see <a href="http://info.appdirect.com/developers/docs/event_references/api_error_codes">Error Codes</a>
     * @throws RestClientException if the event can't be processed
     */
     EventResult assignUser(Event event) throws AppDirectEventException, RestClientException;

    /**
     * Unassign an existing user from a NotesApp account
     *
     * @param event AppDirect event
     * @return response to the AppDirect notification
     * @throws AppDirectEventException if the event triggered an AppDirectErrorCode @see <a href="http://info.appdirect.com/developers/docs/event_references/api_error_codes">Error Codes</a>
     * @throws RestClientException if the event can't be processed
     */
     EventResult unassignUser(Event event) throws AppDirectEventException, RestClientException;


}
