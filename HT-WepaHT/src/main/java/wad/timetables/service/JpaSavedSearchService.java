package wad.timetables.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.timetables.domain.SavedSearch;
import wad.timetables.repository.SavedSearchRepository;

/* @author mhaanran */
@Service
public class JpaSavedSearchService implements SavedSearchService {
 
    @Autowired
    private SavedSearchRepository savedSearchRepository;
    
    @Override
    @Transactional(readOnly=false)
    public SavedSearch createSavedSearch(SavedSearch savedSearch) {
        return savedSearchRepository.save(savedSearch);
    }

    @Override
    @Transactional(readOnly=false)
    public SavedSearch deleteSavedSearch(SavedSearch savedSearch) {
        SavedSearch s = savedSearchRepository.findOne(savedSearch.getId());
        savedSearchRepository.delete(savedSearch);
        return s;
    }

    @Override
    @Transactional(readOnly=true)
    public List<SavedSearch> listSavedSearches(String username) {
        List<SavedSearch> savedSearches = new ArrayList();
        for (SavedSearch savedSearch : savedSearchRepository.findAll()) {
            System.out.println("ss: "+savedSearch.getSearchName()+savedSearch.getUser().getUsername());
            if(savedSearch.getUser().getUsername().equals(username)) {
                savedSearches.add(savedSearch);
            }
        }
        return savedSearches;
    }

}
