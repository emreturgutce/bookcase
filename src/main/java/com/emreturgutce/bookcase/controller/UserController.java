package com.emreturgutce.bookcase.controller;

import com.emreturgutce.bookcase.Constants;
import com.emreturgutce.bookcase.model.User;
import com.emreturgutce.bookcase.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Map<String, Object> userMap) {
        String name = (String) userMap.get("name");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.signup(name, email, password);

        String token = generateJwtToken(user);

        Map<String, String> map = createUserResponse(user);

        map.put("token", token);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.login(email, password);

        String token = generateJwtToken(user);

        Map<String, String> map = createUserResponse(user);

        map.put("token", token);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, List<User>>> findAllUsers()  {
        List<User> users = userService.findAll();

        Map<String, List<User>> map = new HashMap<>();

        map.put("users", users);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Map<String, String> createUserResponse(User user) {
        Map<String, String> map = new HashMap<>();

        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("created_at", user.getCreated_at());
        map.put("updated_at", user.getUpdated_at());

        return map;
    }

    private String generateJwtToken(User user) {
        long timestamp = System.currentTimeMillis();

        return Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_EXPIRATION_TIME))
                .claim("id", user.getId())
                .compact();
    }
}
