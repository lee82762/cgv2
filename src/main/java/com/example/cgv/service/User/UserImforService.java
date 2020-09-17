package com.example.cgv.service.User;

import com.example.cgv.domain.dto.User.UserImforDto;
import com.example.cgv.domain.entity.User;
import com.example.cgv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
@Slf4j

public class UserImforService {

    private final UserRepository userRepository;

    public UserImforService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }


    public UserImforDto UserImfor(Cookie cookie){

       cookie=new Cookie("user","apple");
        String user1=cookie.getValue();
        //User user=userRepository.findByUserID("banana");
        User user=userRepository.findByUserID(user1);

        if(user==null){
            log.info("사용자를 찾을 수 없습니다.");
        }


        return UserImforDto.builder()
                .name(user.getName())
                .userID(user.getUserID())
                .password(user.getPassword())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();

    }
}
