package wad.timetables.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.timetables.domain.JsonFavStops;
import wad.timetables.domain.SavedSearch;
import wad.timetables.domain.Users;
import wad.timetables.repository.SavedSearchRepository;
import wad.timetables.repository.UserRepository;

/* @author mhaanran */
@Service
public class JpaSavedSearchService implements SavedSearchService {
 
    @Autowired
    private SavedSearchRepository savedSearchRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    @Transactional(readOnly=false)
    public SavedSearch createSavedSearch(SavedSearch savedSearch) {     
        return savedSearchRepository.save(savedSearch);      
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteSavedSearch(Long id,Users user) {
        List<SavedSearch> savedSearches = listSavedSearches(user.getUsername());
        for (SavedSearch savedSearch : savedSearches) {
            if(savedSearch.getId()==id) {
                savedSearchRepository.deleteById(id);
//                savedSearchRepository.delete(savedSearch);
//                user.getSavedSearches().remove(savedSearch);
//                userRepository.save(user);
            }
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<SavedSearch> listSavedSearches(String username) {
        List<SavedSearch> savedSearches = new ArrayList();
        for (SavedSearch savedSearch : savedSearchRepository.findAll()) {
            if(savedSearch.getUser().getUsername().equals(username)) {
                savedSearches.add(savedSearch);
            }
        }
        return savedSearches;
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<JsonFavStops> returnFavouriteStops(String username) {
        List<SavedSearch> savedSearches = listSavedSearches(username);
        List<JsonFavStops> jsonFavStops = new ArrayList();
        for (SavedSearch savedSearch : savedSearches) {
            JsonFavStops json = new JsonFavStops();
            json.setSearchName(savedSearch.getSearchName());
            json.setStopName(savedSearch.getStopName());
            json.setStopNumber(savedSearch.getStopNumber());
            jsonFavStops.add(json);
        }
        return jsonFavStops;
    }

}
