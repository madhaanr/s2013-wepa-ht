package wad.timetables.service;

import java.util.List;
import wad.timetables.domain.User;



/* @author mhaanran */

public interface UserService {

    User createUser(User user);
    User findOne(String username);
    boolean userExists(User user);
    User deleteUser(Long id);
    List<User> findAllUsers();
}
