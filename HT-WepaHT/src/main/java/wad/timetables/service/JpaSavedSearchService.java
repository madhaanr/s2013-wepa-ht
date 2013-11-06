package wad.timetables.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.timetables.domain.JsonFavStops;
import wad.timetables.domain.SavedSearch;
import wad.timetables.domain.User;
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
    public SavedSearch deleteSavedSearch(String searchName,User user) {
        List<SavedSearch> savedSearches = listSavedSearches("nsa");
        System.out.println("hi!!DSFSF"+searchName);
        for (SavedSearch savedSearch : savedSearches) {
            System.out.println("SavedSearch name "+savedSearch.getSearchName());
            if(savedSearch.getSearchName().equals(searchName)) {
                System.out.println("hi!!!!");
            }
        }
//        for (SavedSearch savedSearch : savedSearches) {
//            if(savedSearch.getSearchName().equals(searchName)) {
//                User user = userRepository.findOne(savedSearch.getUser().getId());
//                List<SavedSearch> userSavedSearches = user.getSavedSearches();
//                userSavedSearches.remove(savedSearch);               
//                savedSearchRepository.delete(savedSearch);
//                userRepository.save(user);
//                for (SavedSearch savedSearch1 : userSavedSearches) {
//                    System.out.println("searchName: "+savedSearch1.getSearchName());
//                }
//                return savedSearch;
//            }
//        }
        return null; 
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
