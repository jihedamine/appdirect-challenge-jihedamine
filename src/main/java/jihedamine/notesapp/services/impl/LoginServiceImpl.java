package jihedamine.notesapp.services.impl;

import jihedamine.notesapp.exceptions.LoginException;
import jihedamine.notesapp.model.AccountStatus;
import jihedamine.notesapp.model.User;
import jihedamine.notesapp.repositories.CompanyAccountRepository;
import jihedamine.notesapp.repositories.UserRepository;
import jihedamine.notesapp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyAccountRepository companyAccountRepository;

    public User login(String openId) throws LoginException {
        User user = userRepository.findByOpenId(openId);

        if (user == null) {
            throw new LoginException("User not found in NotesApp");
        }

        AccountStatus accountStatus = user.getCompanyAccount().getStatus();
        if (!accountStatus.equals(AccountStatus.ACTIVE)
                && !accountStatus.equals(AccountStatus.FREE_TRIAL)) {
            throw new LoginException(accountStatus);
        }

        return user;
    }


}
