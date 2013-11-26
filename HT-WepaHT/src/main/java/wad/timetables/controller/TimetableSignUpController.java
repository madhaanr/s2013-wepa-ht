package wad.timetables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.timetables.domain.Users;
import wad.timetables.service.UserService;

/* @author mhaanran */

@Controller
public class TimetableSignUpController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="signup",method=RequestMethod.GET)
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new Users());
        return "signup"; 
    } 
    
    @RequestMapping(value="signup",method=RequestMethod.POST)
    public String signupForm(Model model, @ModelAttribute("user") Users user) {
        userService.createUser(user);
        return "success";
    }
    
}
