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
        System.out.println(email);
        User userToUpdate = userRepository.findByEmail(email).orElseThrow();
        userToUpdate.setAllowTexts(user.allowTexts);
        userToUpdate.setAllowEmails(user.allowEmails);
        userRepository.save(userToUpdate);
        return ResponseEntity.ok().build();
    }
}
