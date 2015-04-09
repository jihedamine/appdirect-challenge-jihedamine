package jihedamine.notesapp.controllers;

import jihedamine.notesapp.model.Note;
import jihedamine.notesapp.model.User;
import jihedamine.notesapp.repositories.NoteRepository;
import jihedamine.notesapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Controller
public class NotesController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    private MessageSource messageSource;

    private static Logger log = LoggerFactory.getLogger(NotesController.class);

    @RequestMapping(value = "/user/notes/create", method = {RequestMethod.GET})
    public ModelAndView getNoteCreationForm(@RequestParam String openid) {
        log.debug("Requesting note creation form");

        User user = userRepository.findByOpenId(openid);

        ModelAndView modelAndView = new ModelAndView("notes/create", "user", user);
        modelAndView.addObject(new Note());
        return modelAndView;
    }

    @RequestMapping(value = "/user/notes", method = RequestMethod.POST)
    public ModelAndView createNote(@RequestParam String openid, Note note,
                             BindingResult result, RedirectAttributes redirect, Locale locale) {
        log.debug("Posted note to create");

        User user = userRepository.findByOpenId(openid);
        user.getNotes().add(note);
        userRepository.save(user);

        log.info("New note created");

        redirect.addFlashAttribute("message", messageSource.getMessage("note.create.success", null, locale));
        return new ModelAndView("redirect:/user/notes/list?openid=" + openid, "user", user);
    }

    @RequestMapping(value = "/user/notes/list", method = {RequestMethod.GET})
    public ModelAndView listNotes(@RequestParam String openid) {
        log.debug("Requesting notes list page");

        User user = userRepository.findByOpenId(openid);

        return new ModelAndView("notes/list", "user", user);
    }

    @RequestMapping(value = "/user/notes/{noteId}", method = {RequestMethod.DELETE})
    public ModelAndView deleteNote(@PathVariable String noteId, @RequestParam String openid) {
        log.debug("Requesting note deletion");

        Note note = noteRepository.findOne(Long.valueOf(noteId));
        noteRepository.delete(note);

        return listNotes(openid);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception e) {
        log.error("Unknown exception: " + e.getMessage());
        e.printStackTrace();
        return new ModelAndView("error", "errorMessage", e.getMessage());
    }


}
