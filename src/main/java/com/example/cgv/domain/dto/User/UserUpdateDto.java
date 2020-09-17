package com.example.cgv.domain.dto.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserUpdateDto {
    private String name;
    private String userID;
    private String password;
    private String email;
    private String phone;
    private String address;


    public String setUserID() {

        return "woqja";

    }
}
