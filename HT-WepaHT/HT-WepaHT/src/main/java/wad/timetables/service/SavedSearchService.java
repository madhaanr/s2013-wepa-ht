package wad.timetables.service;

import java.util.List;
import wad.timetables.domain.JsonFavStops;
import wad.timetables.domain.SavedSearch;
import wad.timetables.domain.Users;

/* @author mhaanran */
public interface SavedSearchService {
 
    SavedSearch createSavedSearch(SavedSearch savedSearch);
    SavedSearch deleteSavedSearch(Long id,Users user);
    List<SavedSearch> listSavedSearches(String username);
    List<JsonFavStops> returnFavouriteStops(String username);
}
