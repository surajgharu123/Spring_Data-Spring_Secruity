package com.olx.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "OLX LOGIN DTO")
public class User {

// @ApiModelProperty(value = "ID")
// private int id;

    @ApiModelProperty(value = "FirstName")
    private String firstName;

    @ApiModelProperty(value = "UserName")
    private String userName;

    @ApiModelProperty(value = "LastName")
    private String lastName;

    @ApiModelProperty(value = "Password")
    private String password;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "Phone Number")
    private double phone;

}

//package com.olx.dto;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//
//@ApiModel(value = "olx user dto")
//public class User {
//    @ApiModelProperty(value = " user id ")
//    private int id;
//
//    @ApiModelProperty(value = " User  email id ")
//    private String email;
//
//    @ApiModelProperty(value = " User  first Name ")
//    private String fName;
//
//    @ApiModelProperty(value = " User  last Name ")
//    private String lName;
//
//    @ApiModelProperty(value = " User  password")
//    private String pass;
//
//    @ApiModelProperty(value = " User  phone number ")
//    private long phoneNum;
//
//    @ApiModelProperty(value = " user name ")
//    private String userName;
//}