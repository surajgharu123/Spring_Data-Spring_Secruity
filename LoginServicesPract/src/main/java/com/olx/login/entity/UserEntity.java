package com.olx.login.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "olxusers")
public class UserEntity {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String email;

    private double phone;

}

//package com.olx.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "users")
//
//public class UserEntity {
//    @Id
//    @GeneratedValue
//    private int id;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "f_name")
//    private String fName;
//
//    @Column(name = "l_name")
//    private String lName;
//
//    @Column(name = "pass")
//    private String pass;
//
//    @Column(name = "phoneNum")
//    private long phoneNum;
//
//    @Column(name = "userName")
//    private String userName;
//
//}