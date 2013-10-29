package wad.timetables.service;

import wad.timetables.domain.SavedSearch;

/* @author mhaanran */
public interface SavedSearchService {
 
    SavedSearch createSavedSearch(SavedSearch savedSearch);
    SavedSearch deleteSavedSearch(SavedSearch savedSearch);
    SavedSearch listSavedSearches();
}
