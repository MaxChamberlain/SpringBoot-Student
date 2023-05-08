package com.csc2045.springbootstudenttracker.activity;

import com.csc2045.springbootstudenttracker.authentication.AuthenticationResponse;
import com.csc2045.springbootstudenttracker.authentication.JwtService;
import com.csc2045.springbootstudenttracker.authentication.RegisterRequest;
import com.csc2045.springbootstudenttracker.user.User;
import com.csc2045.springbootstudenttracker.user.UserDAO;
import com.csc2045.springbootstudenttracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityRepository activityRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity<AuthenticationResponse> saveNewActivity(
            @RequestBody ActivityDTO activity
    ) {
        String email = jwtService.extractUsername(activity.userJWT);
        User user = userRepository.findByEmail(email).get();
        Activity newActivity = new Activity();
        newActivity.setUserEmail(user.getStudentEmail());
        newActivity.setType(activity.type);
        newActivity.setTitle(activity.title);
        newActivity.setDescription(activity.description);
        newActivity.setUserId(user);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String strDate = formatter.format(now);
        newActivity.setStartDate(strDate);
        activityRepository.save(newActivity);
        ResponseEntity response = ResponseEntity.ok(String.valueOf(newActivity));
        return response;
    }
}
