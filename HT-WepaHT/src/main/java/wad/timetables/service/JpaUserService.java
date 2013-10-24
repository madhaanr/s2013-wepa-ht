package wad.timetables.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.timetables.repository.SavedSearchRepository;
import wad.timetables.repository.UserRepository;

/* @author mhaanran */
@Service
public class JpaUserService implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SavedSearchRepository savedSearchRepository;
}
