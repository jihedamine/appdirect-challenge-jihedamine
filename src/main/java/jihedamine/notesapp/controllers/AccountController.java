package jihedamine.notesapp.controllers;

import jihedamine.notesapp.model.User;
import jihedamine.notesapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountController {

    private static Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/home", method = {RequestMethod.GET})
    public ModelAndView home(@RequestParam String openid) {
        log.debug("Requesting home page");

        User user = userRepository.findByOpenId(openid);

        return new ModelAndView("index", "user", user);
    }

    @RequestMapping(value = "/user/profile", method = {RequestMethod.GET})
    public ModelAndView profile(@RequestParam String openid) {
        log.debug("Requesting user profile");

        User user = userRepository.findByOpenId(openid);

        return new ModelAndView("profile", "user", user);
    }

    @RequestMapping(value = "/user/account", method = {RequestMethod.GET})
    public ModelAndView account(@RequestParam String openid) {
        log.debug("Requesting user's account page");

        User user = userRepository.findByOpenId(openid);

        return new ModelAndView("account", "user", user);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception e) {
        log.error("Unknown exception: " + e.getMessage());
        e.printStackTrace();
        return new ModelAndView("error", "errorMessage", e.getMessage());
    }


}
