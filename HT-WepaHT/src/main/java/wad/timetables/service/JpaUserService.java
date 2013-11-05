package wad.timetables.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.timetables.domain.User;
import wad.timetables.repository.SavedSearchRepository;
import wad.timetables.repository.UserRepository;

/* @author mhaanran */
@Service
public class JpaUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly=false)
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
 
    @Override
    @Transactional(readOnly=false)
    public User deleteUser(Long id) {
        User user = userRepository.findOne(id);
        userRepository.delete(id);
        return user;
    }

    @Override
    @Transactional(readOnly=true)
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
    @Transactional(readOnly=true)
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public User findOne(String username) {
        
        for (User user : findAllUsers()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
//        if(username.equals("nsa")) {
//            User user = new User();
//            user.setUsername("nsa");
//            user.setPassword("nsa");
//            return user;
//        }
        return null;
    }
}
