package wad.timetables.service;

import java.io.IOException;
import java.util.List;
import wad.timetables.domain.SearchResult;

/* @author mhaanran */

public interface TimetablesService {

//   String searchByStopName(String stopName);
   List<SearchResult> searchByStopName(String stopName)  throws IOException;
   SearchResult searchByStopNumber(String stopNumber) throws IOException;
}
