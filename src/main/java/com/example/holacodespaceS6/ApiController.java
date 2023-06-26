package com.example.holacodespaceS6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/api")
public class ApiController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping(path="/users")
    public @ResponseBody String addUser(@RequestParam String name, @RequestParam String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        userRepository.save(newUser);
        return "User added successfully";
    }

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping(path="/users/{id}")
    public @ResponseBody String editUser(@PathVariable Integer id, @RequestParam String name, @RequestParam String email) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);
            return "User updated successfully";
        }
        return "User not found";
    }

    @GetMapping(path="/users/{id}")
    public @ResponseBody User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @DeleteMapping(path="/users/{id}")
    public @ResponseBody String deleteUser(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return "User deleted successfully";
        }
        return "User not found";
    }

    @GetMapping(path="/report")
    public @ResponseBody List<Map<String, Object>> getReport() {
        String sql = "SELECT CONCAT(name, ' ==> ', email) as mycol FROM User";
        return jdbcTemplate.queryForList(sql);
    }
}

