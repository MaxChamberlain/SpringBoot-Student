package com.csc2045.springbootstudenttracker.activity;

import com.csc2045.springbootstudenttracker.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_activity")
public class Activity {
    @Id
    @GeneratedValue
    private Long id;
    private String userEmail;
    private String type;
    @Temporal(TemporalType.TIMESTAMP)
    private String startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private String endDate;
    private String title;
    private String description;
    @JoinColumn(name = "user_id")
    @ManyToOne()
    private User userId;
}
