package com.csc2045.springbootstudenttracker.twofactorservice;

import java.util.Optional;
import java.util.Random;

import com.csc2045.springbootstudenttracker.authentication.JwtService;
import com.csc2045.springbootstudenttracker.user.User;
import com.csc2045.springbootstudenttracker.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mfa")
public class TwoFactorServiceController {

    @Autowired
    DAOService daoService;
    @Autowired
    SmsService smsService;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/getCode/{email}", method = RequestMethod.GET)
    public ResponseEntity<Object> sendmfaCodeinSMS
            (@PathVariable("email") String email) {
        if(email == null) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        User user = userRepository.findByEmail(email).get();
        String studentPhone = user.getStudentPhone();
        String mfaCode = String.valueOf(Math.round(Math.random() * 1000000));
        System.out.println("mfaCode: " + mfaCode);
        smsService.send2FaCode(studentPhone, mfaCode);
        daoService.update2FAProperties(user, mfaCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/checkCode/{email}/{mfacode}", method = RequestMethod.GET)
    public ResponseEntity<Object> verify(@PathVariable("email") String email, @PathVariable("mfacode") String code) {
        if(email == null) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        User user = userRepository.findByEmail(email).get();
        Long id = user.getId();

        boolean isValid = daoService.checkCode(String.valueOf(id), code);
        System.out.println("isValid: " + isValid);

        if (isValid) return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

