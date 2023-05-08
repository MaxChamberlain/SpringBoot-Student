package com.csc2045.springbootstudenttracker.twofactorservice;

import com.csc2045.springbootstudenttracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.csc2045.springbootstudenttracker.user.User;

import java.util.Optional;

@Repository
public class DAOService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    public void update2FAProperties(User user, String twofacode) {
        user.mfaCode = twofacode;
        userRepository.save(user);
    }

    public boolean checkCode(String id, String code) {
        Optional<User> user = userRepository.findById(Long.parseLong(id));
        if (user.isPresent()) {
            if (user.get().mfaCode.equals(code)) {
                return true;
            }
        }
        return false;
    }
}