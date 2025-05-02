package com.example.sep_drive_backend.models;

import com.example.sep_drive_backend.constants.RoleEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class users{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // Use a separate auto-generated ID as the primary key

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private boolean emailVerified = false;

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;
    //private String profilePicture;

    public users() {}

    public users(String username, String firstName, String lastName, String email, Date birthDate, String password, RoleEnum role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.role = role;
    }

    public RoleEnum getRole() {
        return role;
    }

   public void setRole(RoleEnum role) {
      this.role = role;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

//    public Date getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(Date birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public String getPasswordHash() {
//        return passwordHash;
//    }
//
//    public void setPasswordHash(String passwordHash) {
//        this.passwordHash = passwordHash;
//    }

//    public String getProfilePicture() {
//        return profilePicture;
//    }
//
//    public void setProfilePicture(String profilePicture) {
//        this.profilePicture = profilePicture;
//    }



}
