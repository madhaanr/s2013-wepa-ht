package wad.timetables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.timetables.domain.Users;

/* @author mhaanran */
public interface UserRepository extends JpaRepository<Users, Long> {

}
