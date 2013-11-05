package wad.timetables.service;

import java.util.List;
import wad.timetables.domain.SavedSearch;
import wad.timetables.domain.User;

/* @author mhaanran */
public interface SavedSearchService {
 
    SavedSearch createSavedSearch(SavedSearch savedSearch);
    SavedSearch deleteSavedSearch(String searchName);
    List<SavedSearch> listSavedSearches(String username);
}
