package wad.timetables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.timetables.service.TimetablesService;

/* @author mhaanran */
@Controller
public class TimetablesController {
 
    @Autowired
    private TimetablesService timetablesService;
    
    @RequestMapping(value="search", method=RequestMethod.POST)
    public String search(Model model) {
        String s = timetablesService.search();
        model.addAttribute("result",s);
        return "search";
    }
}
