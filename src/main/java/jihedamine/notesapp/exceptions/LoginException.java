package jihedamine.notesapp.exceptions;

import jihedamine.notesapp.model.AccountStatus;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public class LoginException extends Exception {

    public LoginException(AccountStatus accountStatus) {
        super("User can't access NotesApp because his associated account has the status " + accountStatus);
    }

    public LoginException(String message) {
        super(message);
    }


}
