package com.csc2045.springbootstudenttracker.user;

import com.csc2045.springbootstudenttracker.activity.Activity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String studentFirstName;
    private String studentLastName;
    private String email;
    private String studentPhone;
    private Boolean allowTexts;
    private Boolean allowEmails;
    private String password;

    @OneToMany()
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private List<Activity> activities;

    @GeneratedValue
    @Temporal(TemporalType.TIMESTAMP)
    private String nextInteractionDate;

    @GeneratedValue
    @Temporal(TemporalType.TIMESTAMP)
    private String lastInteractionDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentEmail() {
        return email;
    }

    public void setStudentEmail(String studentEmail) {
        this.email = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public Boolean getAllowTexts() {
        return allowTexts;
    }

    public void setAllowTexts(Boolean allowTexts) {
        this.allowTexts = allowTexts;
    }

    public Boolean getAllowEmails() {
        return allowEmails;
    }

    public void setAllowEmails(Boolean allowEmails) {
        this.allowEmails = allowEmails;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNextInteractionDate() {
        return nextInteractionDate;
    }

    public void setNextInteractionDate(String nextInteractionDate) {
        this.nextInteractionDate = nextInteractionDate;
    }

    public String getLastInteractionDate() {
        return lastInteractionDate;
    }

    public void setLastInteractionDate(String lastInteractionDate) {
        this.lastInteractionDate = lastInteractionDate;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
