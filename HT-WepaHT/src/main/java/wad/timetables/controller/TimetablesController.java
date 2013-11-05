package wad.timetables.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String searchPage(Model model,@ModelAttribute("searchForm") Search searchForm,@ModelAttribute("saveSearch") SavedSearch savedSearch) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(!username.isEmpty()) {
            System.out.println("username:::"+username);
            model.addAttribute("saved",savedSearchService.listSavedSearches(username));
        }  
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
    public String saveSearch(Model model, @ModelAttribute("searchForm") Search searchForm,@ModelAttribute("saveSearch") SavedSearch savedSearch) {
        
        if(savedSearch!=null) {       
            User user = userService.findOne(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());     
            savedSearch.setUser(user);
            savedSearchService.createSavedSearch(savedSearch);
        }
         
        return "redirect:/app/search";
    }
    
    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value="removeSearch", method=RequestMethod.DELETE)
    public String removeSearch(Model model,@ModelAttribute("searchForm") Search searchForm,@ModelAttribute("saveSearch") SavedSearch savedSearch,@RequestParam(required=false) String searchName) {
        
        if(searchName!=null) {
            savedSearchService.deleteSavedSearch(searchName); 
        }    
        return "redirect:/app/search";
    }

    public void setTimetablesService(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }
}
