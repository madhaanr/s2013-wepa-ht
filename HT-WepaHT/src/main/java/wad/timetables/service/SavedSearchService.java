package wad.timetables.service;

import java.util.List;
import wad.timetables.domain.SavedSearch;

/* @author mhaanran */
public interface SavedSearchService {
 
    SavedSearch createSavedSearch(SavedSearch savedSearch);
    SavedSearch deleteSavedSearch(SavedSearch savedSearch);
    List<SavedSearch> listSavedSearches();
}
