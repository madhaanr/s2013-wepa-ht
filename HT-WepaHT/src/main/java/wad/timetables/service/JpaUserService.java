package wad.timetables.service;

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
    
    @Autowired
    private SavedSearchRepository savedSearchRepository;

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
    
    
}
