package com.glz.model;

import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String token;
    private String refreshToken;

//    private Object menuItems;
//
//    private Set<String> authLinks;

}
