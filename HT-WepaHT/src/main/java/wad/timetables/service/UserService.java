package wad.timetables.service;

import java.util.List;
import wad.timetables.domain.Users;



/* @author mhaanran */

public interface UserService {

    Users createUser(Users user);
    Users findOne(String username);
    boolean userExists(Users user);
    Users deleteUser(Long id);
    List<Users> findAllUsers();
}
