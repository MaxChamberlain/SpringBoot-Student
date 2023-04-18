package com.csc2045.springbootstudenttracker.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        ResponseEntity response = ResponseEntity.ok(String.valueOf(authenticationService.register(registerRequest)));
        return response;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        ResponseEntity response = ResponseEntity.ok(String.valueOf(authenticationService.authenticate(request)));
        return response;
    }

}
