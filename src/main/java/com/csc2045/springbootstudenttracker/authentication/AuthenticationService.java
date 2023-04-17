package com.csc2045.springbootstudenttracker.authentication;

import com.csc2045.springbootstudenttracker.user.Role;
import com.csc2045.springbootstudenttracker.user.User;
import com.csc2045.springbootstudenttracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already exists");
        }
        LocalDate today = LocalDate.now();
        LocalDate oneMonthFromToday = today.plusMonths(1);

        var user = new User();
        user.setStudentFirstName(request.getFirstname());
        user.setStudentLastName(request.getLastname());
        user.setStudentEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStudentPhone(request.getPhone());
        user.setRole(Role.USER);
        user.setAllowEmails(false);
        user.setAllowTexts(false);
        user.setLastInteractionDate(today.toString());
        user.setNextInteractionDate(oneMonthFromToday.toString());

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse().builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse().builder()
                .token(jwtToken)
                .build();
    }
}
