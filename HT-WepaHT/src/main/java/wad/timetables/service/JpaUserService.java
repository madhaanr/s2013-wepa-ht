package wad.timetables.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.timetables.domain.Users;
import wad.timetables.repository.UserRepository;

/* @author mhaanran */
@Service
public class JpaUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly=false)
    public Users createUser(Users user) {
        return userRepository.save(user);
    }  
 
    @Override
    @Transactional(readOnly=false)
    public Users deleteUser(Long id) {
        Users user = userRepository.findOne(id);
        userRepository.delete(id);
        return user;
    }

    @Override
    @Transactional(readOnly=true)
    public boolean userExists(Users user) {
        List<Users> listOfUsers = findAllUsers();
        for (Users current : listOfUsers) {
            if (user.getUsername().equals(current.getUsername()) && user.getPassword().equals(current.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Users> findAllUsers() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Users findOne(String username) {       
        for (Users user : findAllUsers()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
