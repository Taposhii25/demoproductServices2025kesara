package com.scaler.demoproductservices2025kesara.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Userdto {
    private Long userId;
    private String username;
    private String email;
//    private List<Role> roles;

//    public static Userdto from(User user){
//        if(user == null) return null;
//        Userdto userdto = new Userdto();
//        userdto.setUsername(user.getName());
//        userdto.setEmail(user.getEmail());
//        userdto.setUserId(user.getId());
//        userdto.setRoles(user.getRoles());
//
//        return userdto;
//    }
}
