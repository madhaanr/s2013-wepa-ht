package wad.timetables.service;

import java.io.IOException;
import java.util.List;
import wad.timetables.domain.SearchResults;

/* @author mhaanran */

public interface TimetablesService {

//   String searchByStopName(String stopName);
   List<SearchResults> searchByStopName(String stopName)  throws IOException;
   SearchResults searchByStopNumber(String stopNumber) throws IOException;
}
