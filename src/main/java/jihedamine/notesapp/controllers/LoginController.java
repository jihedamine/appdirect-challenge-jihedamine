package jihedamine.notesapp.controllers;

import jihedamine.notesapp.exceptions.LoginException;
import jihedamine.notesapp.model.User;
import jihedamine.notesapp.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView login(OpenIDAuthenticationToken authenticationToken) throws LoginException {
        User user = loginService.login(authenticationToken.getIdentityUrl());

        log.info("Successful login");

        return new ModelAndView("index", "user", user);
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView login(@RequestParam String openid) throws LoginException {
        User user = loginService.login(openid);

        log.info("Successful login");

        return new ModelAndView("index", "user", user);
    }

    @ExceptionHandler(value = LoginException.class)
    public ModelAndView handleLoginException(LoginException e) {
        log.error("Login exception " + e.getMessage());
        e.printStackTrace();
        return new ModelAndView("error", "errorMessage", e.getMessage());
    }

}
