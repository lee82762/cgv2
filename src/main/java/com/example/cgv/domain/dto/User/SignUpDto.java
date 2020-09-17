package com.example.cgv.domain.dto.User;

import com.example.cgv.domain.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SignUpDto {


    private String name;
    private String userID;
    private String password;
    private String email;
    private String phone;
    private String address;



    public User toEntity(){
        return User.builder()
                .name(name)
                .userID(userID)
                .password(password)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
    }


  /*  public User toEntity1(){

        User user=new User();

        user.setEmail("##@##@#@");
        user.setUserID("apple");
        user.setPhone("010433113232");
        user.setPassword("wwofl0201!");
        user.setName("테스트12");
        user.setAddress("부산광역시");
        return user;

    }*/
}
