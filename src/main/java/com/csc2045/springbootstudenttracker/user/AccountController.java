package com.csc2045.springbootstudenttracker.user;

import com.csc2045.springbootstudenttracker.authentication.AuthenticationService;
import com.csc2045.springbootstudenttracker.authentication.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/update")
    public ResponseEntity updateAccount(@RequestBody UpdateUserDTO user) {
        String email = jwtService.extractUsername(user.id.toString());
        User userToUpdate = userRepository.findByEmail(email).orElseThrow();
        if(email == null  || email.equals(user.email)) {
            user.email = userToUpdate.getStudentEmail();
        }
        // check if phone number only numbers and 10 digits
        if(user.phone == null || user.phone.equals(userToUpdate.getStudentPhone()) || user.phone.length() != 10) {
            user.phone = userToUpdate.getStudentPhone();
        }
        userToUpdate.setAllowTexts(user.allowTexts);
        userToUpdate.setAllowEmails(user.allowEmails);
        userToUpdate.setStudentEmail(user.email);
        userToUpdate.setStudentPhone(user.phone);
        userRepository.save(userToUpdate);
        return ResponseEntity.ok(jwtService.generateToken(userToUpdate));
    }
}
