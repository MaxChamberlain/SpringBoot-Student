package com.csc2045.springbootstudenttracker.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UpdateUserDTO {
    public boolean allowTexts;
    public boolean allowEmails;
    public String studentNumber;

    public String email;
    public String phone;
    public String id;
}
