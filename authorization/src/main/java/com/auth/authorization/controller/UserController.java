package com.auth.authorization.controller;

import com.auth.authorization.domain.User;
import com.auth.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/person")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<User> findAll() {

        return StreamSupport.stream(
                this.userRepository.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        var user = this.userRepository.findById(id);
        return new ResponseEntity<User>(
                user.orElse(new User()),
                user.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<User>(
                this.userRepository.save(user),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody User user) {
        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.validateUser(id);
        User user = new User();
        user.setId(id);
        this.userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    private void validateUser(int userId) {
        this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
