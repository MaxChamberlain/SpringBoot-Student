package com.csc2045.springbootstudenttracker.application;

import com.csc2045.springbootstudenttracker.authentication.AuthenticationRequest;
import com.csc2045.springbootstudenttracker.authentication.RegisterRequest;
import com.csc2045.springbootstudenttracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class ApplicationController {
    private UserRepository userRepository;
    @Autowired
    public ApplicationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @RequestMapping
    public String home(Model model) {
        return "home";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(Model model) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        model.addAttribute("authenticationRequest", authenticationRequest);
        return "login";
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String register(Model model) {
        RegisterRequest registerRequest = new RegisterRequest();
        model.addAttribute("registerRequest", registerRequest);
        return "register";
    }

    @RequestMapping(value="/students", method=RequestMethod.GET)
    public String students(Model model) {
        model.addAttribute("students", userRepository.findAll());
        return "studentList";
    }
}
