package jihedamine.notesapp.services;

import jihedamine.notesapp.exceptions.LoginException;
import jihedamine.notesapp.model.User;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public interface LoginService {

    /**
     * Login a user to the NotesApp
     *
     * @param openId openid of the user who wants to login
     * @return the authenticated user if login is successful
     */
    User login(String openId) throws LoginException;

}