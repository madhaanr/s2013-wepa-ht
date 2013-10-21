package wad.timetables.service;

import wad.timetables.domain.SearchResults;

/* @author mhaanran */

public interface TimetablesService {

   String search();
   String search(String stopName);
//   String search(Integer stopNumber);
   SearchResults search(Integer stopNumber);
}
