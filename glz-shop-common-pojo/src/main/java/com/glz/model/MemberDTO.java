package com.glz.model;

import com.glz.pojo.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String username;

    private String token;

    private String nickname;

    private String icon;

    private int gender;

    private String birthday;

    private Set<Permission> menus;
}
