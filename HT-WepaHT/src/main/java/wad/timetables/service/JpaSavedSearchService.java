package wad.timetables.service;

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
    @Transactional
    public SavedSearch createSavedSearch(SavedSearch savedSearch) {
        return savedSearchRepository.save(savedSearch);
    }

    @Override
    @Transactional
    public SavedSearch deleteSavedSearch(SavedSearch savedSearch) {
        SavedSearch s = savedSearchRepository.findOne(savedSearch.getId());
        savedSearchRepository.delete(savedSearch);
        return s;
    }

    @Override
    @Transactional
    public List<SavedSearch> listSavedSearches() {
        return (List<SavedSearch>) savedSearchRepository.findAll();
    }

}
