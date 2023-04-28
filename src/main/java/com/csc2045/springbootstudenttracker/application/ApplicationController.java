package com.csc2045.springbootstudenttracker.application;

import com.csc2045.springbootstudenttracker.activity.ActivityRepository;
import com.csc2045.springbootstudenttracker.authentication.AuthenticationRequest;
import com.csc2045.springbootstudenttracker.authentication.AuthenticationService;
import com.csc2045.springbootstudenttracker.authentication.JwtService;
import com.csc2045.springbootstudenttracker.authentication.RegisterRequest;
import com.csc2045.springbootstudenttracker.questionnaire.Question;
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
        return "studentList";
    }

    @RequestMapping(value="/student/{id}", method=RequestMethod.GET)
    public String student(Model model, @PathVariable("id") Long id) {
        model.addAttribute("student", userRepository.findById(id).get());
        return "student";
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

    @RequestMapping(value="/questionnaire/{token}", method=RequestMethod.GET)
    public String questionnaire(
            Model model,
            @PathVariable("token") String token
    ) {
        Question[] questions = new Question[5];
        questions[0] = new Question();
        questions[0].setQuestion("What is your current status?");
        questions[0].setType("radio");
        questions[0].setIsRequired(true);
        questions[0].setOptions(new String[]{"Working", "In School", "Taking a break"});
        questions[0].setQuestionNumber(1);
        questions[1] = new Question();
        questions[1].setQuestion("Have your education plans changed?");
        questions[1].setType("radio");
        questions[1].setIsRequired(true);
        questions[1].setOptions(new String[]{"Yes", "No"});
        questions[1].setQuestionNumber(2);
        questions[2] = new Question();
        questions[2].setQuestion("Have you halted pursuing new employment opportunities?");
        questions[2].setType("radio");
        questions[2].setIsRequired(true);
        questions[2].setOptions(new String[]{"Yes", "No"});
        questions[2].setQuestionNumber(3);
        questions[3] = new Question();
        questions[3].setQuestion("Have you achieved a job in your desired field?");
        questions[3].setType("radio");
        questions[3].setIsRequired(true);
        questions[3].setOptions(new String[]{"Yes", "No"});
        questions[3].setQuestionNumber(4);
        questions[4] = new Question();
        questions[4].setQuestion("What is your current job title OR school major?");
        questions[4].setType("text");
        questions[4].setIsRequired(true);
        questions[4].setOptions(new String[]{});
        questions[4].setQuestionNumber(5);
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).get();
        model.addAttribute("user", user);
        model.addAttribute("questions", questions);
        return "form";
    }
}
