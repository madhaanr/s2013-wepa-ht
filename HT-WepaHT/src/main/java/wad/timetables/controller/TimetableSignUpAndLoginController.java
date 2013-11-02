package wad.timetables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import wad.timetables.domain.SavedSearch;
import wad.timetables.domain.User;
import wad.timetables.service.SavedSearchService;
import wad.timetables.service.UserService;

/* @author mhaanran */

@Controller
public class TimetableSignUpAndLoginController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SavedSearchService savedSearchService;
    
    @RequestMapping(value="signup",method=RequestMethod.GET)
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "signup"; 
    } 
    
    @RequestMapping(value="signup",method=RequestMethod.POST)
    public String signupForm(Model model, @ModelAttribute("user") User user) {
        userService.createUser(user);
        return "success";
    }
     
//    @RequestMapping(value="login", method=RequestMethod.GET)
//    public String showLoginPage(Model model, @ModelAttribute(value="user") User user) {
//        model.addAttribute("user", new User());
//        return "login";
//    }
//    
// 
//    @RequestMapping(value="login", method=RequestMethod.POST)
//    public String loginForm(Model model, @ModelAttribute(value="user") User user) {
////        model.addAttribute("user", user);
//        return "search";
//    }
    
    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value="authenticated", method=RequestMethod.GET)
//    @ResponseBody
    public String savedSearches(Model model) {
        model.addAttribute("searchToSave", new SavedSearch() ); 
        return "authenticated"; 
    }
    
    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value="authenticated", method=RequestMethod.POST)
    public String saveSearch(Model model, @ModelAttribute(value="searchToSave") SavedSearch savedSearch) {
        model.addAttribute(savedSearch);
        return "authenticated";
    }
    @RequestMapping(value="",method=RequestMethod.POST) 
    public String logout() {
        
        return "logout";
    }
}
