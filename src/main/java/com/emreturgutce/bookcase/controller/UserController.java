package com.emreturgutce.bookcase.controller;

import com.emreturgutce.bookcase.model.User;
import com.emreturgutce.bookcase.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Map<String, Object> userMap) throws Exception {
        String name = (String) userMap.get("name");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.create(name, email, password);

        Map<String, String> map = new HashMap<>();

        map.put("name", user.getName());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, List<User>>> findAllUsers() throws Exception {
        List<User> users = userService.findAll();

        Map<String, List<User>> map = new HashMap<>();

        map.put("users", users);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
