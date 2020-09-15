package com.glz.model;

import com.glz.pojo.RoleMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String nickname;

    private String token;

    private String refreshToken;

    private RoleMenu roleMenu;

}
