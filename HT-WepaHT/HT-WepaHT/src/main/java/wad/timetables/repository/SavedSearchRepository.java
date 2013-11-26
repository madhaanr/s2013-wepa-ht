package wad.timetables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.timetables.domain.SavedSearch;

/* @author mhaanran */
public interface SavedSearchRepository extends JpaRepository<SavedSearch, Long>{

}
