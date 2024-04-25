package com.example.fullstack.domain.service;

import com.example.fullstack.domain.exeptions.UserIdNotFoundException;
import com.example.fullstack.domain.exeptions.UserNotFoundException;
import com.example.fullstack.domain.repository.UserRepository;
import com.example.fullstack.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInterfaceImp implements UserInterface<User> {

    private final UserRepository userRepository;

    @Autowired
    public UserInterfaceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    };

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<User> getById(Object id) {
        try{
            Long  newId = Long.parseLong(id.toString());
            User optionalUser = userRepository.findById(newId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            return ResponseEntity.ok(optionalUser);

        }catch (NumberFormatException e){
            throw new UserIdNotFoundException("You have entered a letter or another character other than a Long type Number");
        }
    }

    @Override
    public ResponseEntity<User> save(User userSave) {
        userRepository.save(userSave);
        return ResponseEntity.ok(userSave);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<User> update(Object id, User userUpdate) {
        try{
            Long newId = Long.parseLong(id.toString());
            Optional<User> optionalUser = userRepository.findById(newId);
            if(optionalUser.isPresent()){

                User userSend = optionalUser.get();
                userSend.setUsername(userUpdate.getUsername());
                userSend.setPassword(userUpdate.getPassword());
                userSend.setEmail(userUpdate.getEmail());
                userRepository.save(userSend);

                return ResponseEntity.ok(userSend);
            } else {
                throw new UserNotFoundException("Admin not found by said ID to update");
            }
        }catch (NumberFormatException e){
            throw new UserIdNotFoundException("You have entered a letter or/or another character other than a Long type Number");
        }
    }
}

