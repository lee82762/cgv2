package com.example.cgv.domain.dto.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserImforDto {

    private String name;
    private String userID;
    private String password;
    private String email;
    private String phone;
    private String address;

}
