package wad.timetables.service;

import java.io.IOException;
import java.util.List;
import wad.timetables.domain.JsonStop;
import wad.timetables.domain.SearchResult;

/* @author mhaanran */
public interface TimetablesService {

    List<SearchResult> searchByStopName(String stopName) throws IOException;
    SearchResult searchByStopNumber(String stopNumber) throws IOException;
    JsonStop jsonStop(String stopNumber) throws IOException;
}
