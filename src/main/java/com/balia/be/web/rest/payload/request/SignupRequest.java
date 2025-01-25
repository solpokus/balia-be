package com.balia.be.web.rest.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Set;

public class SignupRequest {
    @Size(min = 3, max = 25)
    private String username;

    @Size(max = 50)
    @Email
    private String email;

    private Set<String> roles;

    @Size(min = 6, max = 40)
    private String password;
    
    private String firstName;

    private String createdBy;

    private Date createdDate;
    
    private String mobileNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public @Size(min = 6, max = 40) String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "SignupRequest{" + "username='" + username + '\'' + ", email='" + email + '\'' + ", roles=" + roles + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", mobileNumber='" + mobileNumber + '}';
    }
}