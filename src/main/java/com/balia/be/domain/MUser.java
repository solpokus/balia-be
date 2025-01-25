package com.balia.be.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A m_user.
 */
@Entity
@Table(name = "m_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "login")
        })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MUser implements Serializable {

//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "login", nullable = false)
    private String login;

//    @NotNull
    @Column(name = "password_hash")
    private String password;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email")
    private String email;
    
    @NotNull
    @Column(name = "activated", nullable = false)
    private Integer activated;

//    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

//    @NotNull
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

//    @NotNull
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

//    @NotNull
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    
    @Column(name = "activation_key")
    private String activationKey;

    @Column(name = "mobile_number")
    private String mobileNumber;
    

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "m_role_user", joinColumns = @JoinColumn(name = "m_user_id"), inverseJoinColumns = @JoinColumn(name = "m_role_id"))
    private Set<MRole> roles = new HashSet<>();

    public MUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
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

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public Set<MRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<MRole> roles) {
        this.roles = roles;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public MUser(String login, String password, String email, Integer activated, String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate, Set<MRole> roles) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.activated = activated;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MUser mUser = (MUser) o;
        if (mUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MUser{" + "id=" + id + ", login='" + login + '\'' + ", email='" + email + '\'' + ", activated=" 
                + activated + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", lastModifiedBy='" 
                + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate + ", roles=" + roles + '}';
    }
}
