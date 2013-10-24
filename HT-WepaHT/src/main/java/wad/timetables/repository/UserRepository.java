package wad.timetables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.timetables.domain.User;

/* @author mhaanran */
public interface UserRepository extends JpaRepository<User, Long> {

}
