package com.validation.Validation.service;

import com.validation.Validation.model.User;
import com.validation.Validation.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public ResponseEntity<Map> addUser(User user) {
        User userObj = new User();
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;

        try {
                userObj = userRepository.saveAndFlush(user);
                map.put("status_code", "201");
                map.put("message", "Data saved successfully");
                status = HttpStatus.CREATED;

        }catch (Exception ex) {
            map.put("status_code", "500");
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  addState{} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

}
