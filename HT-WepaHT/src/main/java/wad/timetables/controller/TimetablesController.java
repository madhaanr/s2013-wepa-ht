package wad.timetables.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import wad.timetables.domain.JsonFavStops;
import wad.timetables.domain.JsonStop;
import wad.timetables.domain.SavedSearch;
import wad.timetables.domain.Search;
import wad.timetables.domain.SearchResult;
import wad.timetables.domain.Users;
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
    public String searchPage(Model model, @ModelAttribute("searchForm") Search searchForm,
            @ModelAttribute("saveSearch") SavedSearch savedSearch) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (!username.isEmpty()) {
            model.addAttribute("saved", savedSearchService.listSavedSearches(username));
        }
        return "search";
    }

    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("searchForm") Search searchForm,
            @ModelAttribute("saveSearch") SavedSearch savedSearch) throws IOException {

        String error = "Stop could not be found by search parameter used";

        if (!searchForm.getStopNumber().isEmpty()) {
            SearchResult searchByStopNumber = timetablesService.searchByStopNumber(searchForm.getStopNumber());
            if (searchByStopNumber != null) {
                model.addAttribute("results", searchByStopNumber);
            } else {
                model.addAttribute("error", error);
            }
        } else if (!searchForm.getStopName().isEmpty()) {
            List<SearchResult> searchByStopName = timetablesService.searchByStopName(searchForm.getStopName());
            if (searchByStopName != null) {
                model.addAttribute("stopName", searchByStopName);
            } else {
                model.addAttribute("error", error);
            }
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (!username.isEmpty()) {
            model.addAttribute("saved", savedSearchService.listSavedSearches(username));
        }

        return "search";
    }

    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value = "saveSearch", method = RequestMethod.POST)
    public String saveSearch(Model model, @ModelAttribute("searchForm") Search searchForm,
            @ModelAttribute("saveSearch") SavedSearch savedSearch) {

        if (savedSearch != null) {
            Users user = userService.findOne(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            savedSearch.setUser(user);
            savedSearchService.createSavedSearch(savedSearch);
        }

        return "redirect:/app/search";
    }

    @PreAuthorize("hasRole('auth')")
    @RequestMapping(value = "search/{id}/removeSearch", method = RequestMethod.DELETE)
    public String removeSearch(@PathVariable("id") Long id) {
        Users user = userService.findOne(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        savedSearchService.deleteSavedSearch(id, user);
        return "redirect:/app/search";
    }

    @RequestMapping(value = "json/stops/{username}", method = RequestMethod.GET)
    @ResponseBody
    public List<JsonFavStops> jsonStops(@PathVariable("username") String username) {
        return savedSearchService.returnFavouriteStops(username);
    }

    @RequestMapping(value = "json/timetable/{stopNumber}", method = RequestMethod.GET)
    @ResponseBody
    public JsonStop jsonStopTimetable(@PathVariable("stopNumber") String stopNumber) throws IOException {
        return timetablesService.jsonStop(stopNumber);
    }

    public void setTimetablesService(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }
}
