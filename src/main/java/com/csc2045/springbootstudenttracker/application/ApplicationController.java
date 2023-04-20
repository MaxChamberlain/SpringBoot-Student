package com.csc2045.springbootstudenttracker.application;

import com.csc2045.springbootstudenttracker.activity.ActivityRepository;
import com.csc2045.springbootstudenttracker.authentication.AuthenticationRequest;
import com.csc2045.springbootstudenttracker.authentication.AuthenticationService;
import com.csc2045.springbootstudenttracker.authentication.JwtService;
import com.csc2045.springbootstudenttracker.authentication.RegisterRequest;
import com.csc2045.springbootstudenttracker.user.User;
import com.csc2045.springbootstudenttracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class ApplicationController {
    private UserRepository userRepository;
    private JwtService jwtService;
    private AuthenticationService authenticationService;
    private ActivityRepository activityRepository;
    @Autowired
    public ApplicationController(UserRepository userRepository, JwtService jwtService, AuthenticationService authenticationService, ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.activityRepository = activityRepository;
    }
    @RequestMapping
    public String home(Model model) {
        return "redirect:/home";
    }


    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String homeDefault(Model model) {
        return "home";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(Model model) {
        if (authenticationService.isAuthenticated()) {
            return "home";
        }
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        model.addAttribute("authenticationRequest", authenticationRequest);
        return "login";
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String register(Model model) {
        if (authenticationService.isAuthenticated()) {
            return "home";
        }
        RegisterRequest registerRequest = new RegisterRequest();
        model.addAttribute("registerRequest", registerRequest);
        return "register";
    }

    @RequestMapping(value="/students", method=RequestMethod.GET)
    public String students(Model model) {
        model.addAttribute("students", userRepository.findAll());
        System.out.println(userRepository.findAll());

        return "studentList";
    }

    @RequestMapping(value="/account/{token}", method=RequestMethod.GET)
    public String account(
            Model model,
            @PathVariable("token") String token
    ) {
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).get();
        model.addAttribute("user", user);
        return "account";
    }
}
