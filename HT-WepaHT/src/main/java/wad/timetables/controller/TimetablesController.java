package wad.timetables.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import wad.timetables.domain.SavedSearch;
import wad.timetables.domain.Search;
import wad.timetables.domain.SearchResult;
import wad.timetables.domain.User;
import wad.timetables.service.SavedSearchService;
import wad.timetables.service.TimetablesService;
import wad.timetables.service.UserService;

/* @author mhaanran */
@Controller
@SessionAttributes("user")
public class TimetablesController {

    @Autowired
    private TimetablesService timetablesService;
    
    @Autowired
    private SavedSearchService savedSearchService;
   
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchPage(Model model,@ModelAttribute("searchForm") Search searchForm,@ModelAttribute("saveSearch") SavedSearch savedSearch,HttpServletRequest request) {
//        User user = userService.findOne(request.getUserPrincipal().getName());
        String username = request.getUserPrincipal().getName();
        if(!username.isEmpty()) {
            System.out.println("username:::"+username);
            model.addAttribute("saved",savedSearchService.listSavedSearches(username));
        }
        
//        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user"+user);
        System.out.println("cred: "+username); 
        return "search"; 
    }
   
    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("searchForm") Search searchForm,@ModelAttribute("saveSearch") SavedSearch savedSearch) throws IOException {
        String error = "Stop could not be found by search parameter used";
        
        if (!searchForm.getStopNumber().isEmpty()) {
            SearchResult searchByStopNumber = timetablesService.searchByStopNumber(searchForm.getStopNumber());
            if (searchByStopNumber != null) {
                model.addAttribute("results", searchByStopNumber);
            }
        } 
        else if (!searchForm.getStopName().isEmpty()) {
            List<SearchResult> searchByStopName = timetablesService.searchByStopName(searchForm.getStopName());
            if (searchByStopName != null) {
                model.addAttribute("stopName", searchByStopName);
            }
        }   
        else {
            model.addAttribute("error", error);
        }
        
        return "search";
    }

    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value = "saveSearch", method = RequestMethod.POST)
    public String saveSearch(Model model, @ModelAttribute("searchForm") Search searchForm,@ModelAttribute("saveSearch") SavedSearch savedSearch, HttpServletRequest request) {
        
        if(savedSearch!=null) {
//            Principal userPrincipal = request.getUserPrincipal();
//            System.out.println("user: "+userPrincipal.getName());
            
            User user = userService.findOne(request.getUserPrincipal().getName());
            
            savedSearch.setUser(user);
            savedSearchService.createSavedSearch(savedSearch);
        }
         
        return "redirect:/app/search";
    }

    public void setTimetablesService(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }
}
