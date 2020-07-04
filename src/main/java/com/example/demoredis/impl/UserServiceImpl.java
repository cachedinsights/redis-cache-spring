package com.example.demoredis.impl;

import com.example.demoredis.domain.User;
import com.example.demoredis.repository.UserRepository;
import com.example.demoredis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(long id) {
       logger.debug(" >> UserService : Entering getUser");
       Optional<User> userOp =  userRepository.findById(id);

       if(userOp!=null){
           logger.debug(" << UserService : Exiting getUser");
           return userOp.get();
       }
       else{
           logger.debug(" << UserService : No Such User Exists : Exiting getUser");
           return null;
       }
    }

    @Override
    public List<User> getAll() {
        logger.debug(" >> UserService : Entering getAll");
        logger.debug(" << UserService : Exiting getAll");
        return userRepository.findAll();

    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        logger.debug(">> UserService : Entering update");
        Long id = user.getId();
        User userInDb = getUser(id);
        if(userInDb!=null){
            logger.debug(">> UserService : User updated : Exiting update");
            return create(user);
        }
        else
        {
            logger.debug(">> UserService : User with this id does not exist : Exiting update");
            return null;
        }
    }

    @Override
    public User create(User user) {
        logger.debug(" >> UserService : Entering create");

       User userToRet =  userRepository.save(user);
        logger.debug(" << UserService : Exiting create");
       return userToRet;
    }
}
