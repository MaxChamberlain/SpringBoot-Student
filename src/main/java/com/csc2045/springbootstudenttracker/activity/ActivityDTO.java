package com.csc2045.springbootstudenttracker.activity;

import com.csc2045.springbootstudenttracker.user.User;
import jakarta.persistence.*;

public class ActivityDTO {
    public String userJWT;
    public String type;
    public String title;
    public String description;
}
