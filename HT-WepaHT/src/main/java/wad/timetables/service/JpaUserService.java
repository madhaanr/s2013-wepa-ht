package wad.timetables.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.timetables.domain.User;
import wad.timetables.repository.SavedSearchRepository;
import wad.timetables.repository.UserRepository;

/* @author mhaanran */
@Service
public class JpaUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
 
    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findOne(id);
        userRepository.delete(id);
        return user;
    }

    @Override
    public boolean userExists(User user) {
        List<User> listOfUsers = findAllUsers();
        for (User current : listOfUsers) {
            if (user.getUsername().equals(current.getUsername()) && user.getPassword().equals(current.getPassword())) {
                System.out.println("u: "+user.getUsername()+" p:"+user.getPassword());
                return true;
            }
        }
        return false;

    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
