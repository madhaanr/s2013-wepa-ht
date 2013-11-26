package wad.timetables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import wad.timetables.domain.SavedSearch;

/* @author mhaanran */
@Transactional
public interface SavedSearchRepository extends JpaRepository<SavedSearch, Long>{
    
    @Modifying
    @Transactional
    @Query("delete from SavedSearch where id=?1")
    void deleteById(Long id);
}
