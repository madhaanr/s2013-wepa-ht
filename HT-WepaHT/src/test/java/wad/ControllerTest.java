package wad;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import wad.timetables.controller.TimetablesController;
import wad.timetables.service.TimetablesService;
import static org.junit.Assert.*;
import org.springframework.ui.Model;
import wad.timetables.domain.Search;
import wad.timetables.domain.SearchResult;

/* @author mhaanran */
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    private TimetablesController timetablesController;
    
    
    @Mock
    private TimetablesService timetablesService;
    
    @Mock
    private Model model;
    
    @Before
    public void setUp() {
        timetablesController = new TimetablesController();
        timetablesController.setTimetablesService(timetablesService);      
    }
    
    @Test
    public void testSearch() throws IOException {
        SearchResult searchResult = new SearchResult();
        Search search = new Search();
        search.setStopNumber("1923");
        searchResult.setName_fi("kuusitie");
        
        when(timetablesService.searchByStopNumber("1923")).thenReturn(searchResult);
        
        String result = timetablesController.search(model, search);
        System.out.println("result "+result);
        assertNotNull(result);
        
    }
}
