package com.glz.model;

import com.glz.pojo.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String userId;

    private String username;

    private String token;

    private String nickname;

    private String icon;

    private int gender;

    private String phone;

    private String email;

    private String birthday;

    private Set<Permission> menus;
}
