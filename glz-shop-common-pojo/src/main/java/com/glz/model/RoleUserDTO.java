package com.glz.model;


import com.glz.pojo.Role;
import com.glz.pojo.User;
import lombok.Data;

@Data
public class RoleUserDTO {

    private User userId;

    private Role roleId;

}
