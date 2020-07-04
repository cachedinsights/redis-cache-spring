package com.example.demoredis.controller;

import com.example.demoredis.domain.User;
import com.example.demoredis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private  final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    @Cacheable(value = "users",key = "#id",unless = "#result.id<5")
    public User getUser(@PathVariable long id){
        logger.debug(" >> UserController : /user/{} call : ",id);
        return userService.getUser(id);

    }

    @PostMapping("/user")
    public User create(@RequestBody User user){
        logger.debug(" >> UserController : /user : ",user.toString());
        return userService.create(user);
    }
    @GetMapping("/users")
     public List<User> getAll(){
        logger.debug(" >> UserController : /users : ");

        return userService.getAll();
     }

     @PutMapping("/update")
     @CachePut(value = "users",key = "#user.id")
     public User updateUser(@RequestBody User user)
     {
         logger.debug(" >> UserController : /update : ",user.toString());
         return userService.update(user);
     }

     @DeleteMapping("/delete/{id}")
     @CacheEvict(value = "users",allEntries = false,key = "#id")
     public void deleteUser(@PathVariable Long id)
     {
          logger.debug(" >> UserController : /delete : ",id);
          userService.delete(id);
          logger.debug(" << UserController : /delete : ",id);

     }
}
