package com.dhvanil.apibackend.controllers;

import com.dhvanil.apibackend.exceptions.UserNotFoundException;
import com.dhvanil.apibackend.models.User;
import com.dhvanil.apibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UseController {

    @Autowired
    private UserRepository userrepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userrepository.save(newUser);
    }

    @GetMapping("/user")
    List<User>  getAllUsers(){
        return userrepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userrepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }


    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userrepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userrepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userrepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userrepository.deleteById(id);
        return "User with id" + " " + id + " " + "has been deleted";

    }

}
