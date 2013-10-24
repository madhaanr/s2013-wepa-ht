package wad.timetables.service;

import wad.timetables.domain.User;



/* @author mhaanran */

public interface UserService {

    User createUser(User user);
    User deleteUser(Long id);
}
