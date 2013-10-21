package wad.timetables.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wad.timetables.domain.Search;
import wad.timetables.domain.SearchResults;
import wad.timetables.service.TimetablesService;

/* @author mhaanran */
@Controller
public class TimetablesController {
 
    @Autowired 
    private TimetablesService timetablesService;
     
    @RequestMapping(value="search", method=RequestMethod.GET)
    public String searchPage(@ModelAttribute("searchForm") Search search) {
        return "search"; 
    }   
    
    @RequestMapping(value="search", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String search(Model model,@ModelAttribute("searchForm") Search searchForm) {
        System.out.println("stopName: "+searchForm.getStopName()+" stopNumber: "+searchForm.getStopNumber());
        if(!searchForm.getStopName().isEmpty()&&searchForm.getStopNumber()!=null) {
            model.addAttribute("result",timetablesService.search(searchForm.getStopNumber()));
        }
        else if(!searchForm.getStopName().isEmpty()) {
            model.addAttribute("result",timetablesService.search(searchForm.getStopName()));
        }
//        else if(searchForm.getStopNumber()!=null) {
//            String s = timetablesService.search(searchForm.getStopNumber());
//            model.addAttribute("result",s);
//        }
        else if(searchForm.getStopNumber()!=null) {
            SearchResults s = timetablesService.search(searchForm.getStopNumber());
            model.addAttribute("result",s);
        }
         
        return "search";
    }
}
