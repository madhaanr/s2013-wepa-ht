package wad.timetables.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.timetables.domain.Search;
import wad.timetables.service.TimetablesService;

/* @author mhaanran */
@Controller
public class TimetablesController {

    @Autowired
    private TimetablesService timetablesService;
  
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchPage(@ModelAttribute("searchForm") Search searchForm) {
        return "search";
    }
 
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("searchForm") Search searchForm) throws IOException {
        
        if (!searchForm.getStopName().isEmpty() && searchForm.getStopNumber() != null) {
            model.addAttribute("results", timetablesService.search(searchForm.getStopNumber()));
        }   
        else if (!searchForm.getStopName().isEmpty()) {
            model.addAttribute("result", timetablesService.search(searchForm.getStopName()));
        } 
        else if (searchForm.getStopNumber() != null) {
            model.addAttribute("results", timetablesService.search(searchForm.getStopNumber()));
        }
        return "search";
    }
}
