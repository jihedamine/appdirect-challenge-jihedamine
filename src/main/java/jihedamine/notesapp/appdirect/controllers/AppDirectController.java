package jihedamine.notesapp.appdirect.controllers;

import jihedamine.notesapp.appdirect.exceptions.AppDirectEventException;
import jihedamine.notesapp.appdirect.model.APIErrorCode;
import jihedamine.notesapp.appdirect.model.Event;
import jihedamine.notesapp.appdirect.model.EventResult;
import jihedamine.notesapp.appdirect.model.EventType;
import jihedamine.notesapp.appdirect.services.AppDirectService;
import jihedamine.notesapp.appdirect.services.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@RestController
@RequestMapping(value = "/appdirect/events")
public class AppDirectController {
    private static Logger log = LoggerFactory.getLogger(AppDirectController.class);

    @Autowired
    AppDirectService appDirectService;

    @Autowired
    OAuthService oAuthService;

    @RequestMapping(
            value = "/subscription/create",
            method = {RequestMethod.GET},
            produces = "application/xml"
    )
    @ResponseStatus(HttpStatus.OK)
    public EventResult createSubscription(@RequestParam String url) throws AppDirectEventException {
        Event event = oAuthService.getEventFromUrl(url, EventType.SUBSCRIPTION_ORDER);
        return appDirectService.createSubscription(event);
    }

    @RequestMapping(
            value = "/subscription/change",
            method = {RequestMethod.GET},
            produces = "application/xml"
    )
    @ResponseStatus(HttpStatus.OK)
    public EventResult changeSubscription(@RequestParam String url) throws AppDirectEventException {
        Event event = oAuthService.getEventFromUrl(url, EventType.SUBSCRIPTION_CHANGE);
        return appDirectService.changeSubscription(event);
    }

    @RequestMapping(
            value = "/subscription/cancel",
            method = {RequestMethod.GET},
            produces = "application/xml"
    )
    @ResponseStatus(HttpStatus.OK)
    public EventResult cancelSubscription(@RequestParam String url) throws AppDirectEventException {
        Event event = oAuthService.getEventFromUrl(url, EventType.SUBSCRIPTION_CANCEL);
        return appDirectService.cancelSubscription(event);
    }

    @RequestMapping(
            value = "/subscription/notice",
            method = {RequestMethod.GET},
            produces = "application/xml"
    )
    @ResponseStatus(HttpStatus.OK)
    public EventResult noticeSubscription(@RequestParam String url) throws AppDirectEventException {
        Event event = oAuthService.getEventFromUrl(url, EventType.SUBSCRIPTION_NOTICE);
        return appDirectService.noticeSubscription(event);
    }

    @RequestMapping(
            value = "/user/assignment",
            method = {RequestMethod.GET},
            produces = "application/xml"
    )
    @ResponseStatus(HttpStatus.OK)
    public EventResult assignUser(@RequestParam String url) throws AppDirectEventException {
        Event event = oAuthService.getEventFromUrl(url, EventType.USER_ASSIGNMENT);
        return appDirectService.assignUser(event);
    }

    @RequestMapping(
            value = "/user/unassignment",
            method = {RequestMethod.GET},
            produces = "application/xml"
    )
    @ResponseStatus(HttpStatus.OK)
    public EventResult unassignUser(@RequestParam String url) throws AppDirectEventException {
        Event event = oAuthService.getEventFromUrl(url, EventType.USER_UNASSIGNMENT);
        return appDirectService.unassignUser(event);
    }

    @ExceptionHandler(value = AppDirectEventException.class)
    @ResponseStatus(HttpStatus.OK)
    public EventResult handleAppDirectException(AppDirectEventException e) {
        log.error("AppDirectEvent exception: " + e.getMessage());
        e.printStackTrace();
        return EventResult.error(e.getErrorCode());
    }

    /**
     * RestClientException is thrown by oAuthRestTemplate
     * when it fails to fetch the event
     */
    @ExceptionHandler(value = RestClientException.class)
    @ResponseStatus(HttpStatus.OK)
    public EventResult handleFetchEventException(RestClientException e) {
        log.error("Fetch event exception: " + e.getMessage());
        e.printStackTrace();
        return EventResult.error(APIErrorCode.INVALID_RESPONSE);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public EventResult handleException(Exception e) {
        log.error("Unknown exception: " + e.getMessage());
        e.printStackTrace();
        return EventResult.error(APIErrorCode.UNKNOWN_ERROR);
    }

}
