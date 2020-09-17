package com.example.cgv.domain.entity;

import com.example.cgv.domain.dto.User.UserUpdateDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    @NotNull
    private String name;

    @Column(length = 10)
    @NotNull
    private String  userID;

    @Column(length = 10)
    @NotNull
    private String password;

    @Column(length = 20)

    private String email;

    @Column(length = 15)

    private String phone;

    @Column(length = 50)

    private String address;

    @Builder
    public User(Long id, String name, String userID, String password, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.email=email;
        this.phone=phone;
        this.address=address;
    }

    public void UserUpdate(UserUpdateDto userUpdateDto) {
        this.name = userUpdateDto.getName();
        this.userID = userUpdateDto.getUserID();
        this.password = userUpdateDto.getPassword();
        this.email=userUpdateDto.getEmail();
        this.phone=userUpdateDto.getPhone();
        this.address=userUpdateDto.getAddress();
    }

    public void UserUpdate1(UserUpdateDto userUpdateDto) {

        this.userID = userUpdateDto.setUserID();

    }
}
