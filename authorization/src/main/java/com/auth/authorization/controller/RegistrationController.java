package com.auth.authorization.controller;

import com.auth.authorization.domain.User;
import com.auth.authorization.repository.RoleRepository;
import com.auth.authorization.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationController(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String pageRegistration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody
    String userRegistration(@RequestBody User user, ModelMap model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String account = null;
        User userDB = null;
        try {
            userDB = this.userRepository.getByUserNameAndPassword(user.getUsername(), user.getPassword()).orElse(null);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        if (userDB == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(roleRepository.findById(1).get());
            userRepository.save(user);
            account = "Your account is opened.";
        } else {
            model.addAttribute("account", "error");
            account = "Your account is not opened, change your username";
        }
        return account;
    }

}
