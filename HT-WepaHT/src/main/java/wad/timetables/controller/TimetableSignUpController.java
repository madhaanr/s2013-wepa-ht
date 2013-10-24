package wad.timetables.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import wad.timetables.domain.User;

/* @author mhaanran */

@SessionAttributes("user")
@Controller
public class TimetableSignUpController {

    @RequestMapping(value="signup",method=RequestMethod.GET)
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "signup"; 
    }
    
    @RequestMapping(value="signup",method=RequestMethod.POST)
    public String signupForm(Model model, @ModelAttribute("user") User user) {
        
        return "success";
    }
    
    @RequestMapping(value="login", method=RequestMethod.POST)
    public String login(Model model, @ModelAttribute("user") User user) {
         
        return "login";
    }
}
