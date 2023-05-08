package com.csc2045.springbootstudenttracker.twofactorservice;

import com.twilio.rest.api.v2010.account.Call;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

    private final static String ACCOUNT_SID = "AC7e3d1267230f2c97fe0b815aa8265a20";
    private final static String AUTH_ID = "5fcad046d1b86de89744e9f9ac1519e1";

//    private final static String ACCOUNT_SID = "ACa70d7b77222323629b10ce5b82204bac";
//    private final static String AUTH_ID = "1a1f85c301cacc4968444dbd62f2e8ff";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_ID);
    }

    public boolean send2FaCode(String studentPhone, String twoFaCode) {

        Message.creator(new PhoneNumber(studentPhone), new PhoneNumber("18775609718"),
                "Your Two Factor Authentication code is: "+ twoFaCode).create();

        return true;


    }
}