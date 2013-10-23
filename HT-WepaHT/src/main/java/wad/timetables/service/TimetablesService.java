package wad.timetables.service;

import java.io.IOException;
import wad.timetables.domain.SearchResults;

/* @author mhaanran */

public interface TimetablesService {

   String search();
   String searchByStopName(String stopName);
   SearchResults searchByStopNumber(String stopNumber) throws IOException;
}
