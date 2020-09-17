package com.example.cgv.service.User;

import com.example.cgv.domain.entity.User;
import com.example.cgv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
@Slf4j
public class UserDeleteService {
    private final UserRepository userRepository;


    public UserDeleteService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    public void UserDelete(Cookie cookie){
        Cookie cookie1=new Cookie("user","woqja");

        String user2=cookie1.getValue();
        User user= userRepository.findByUserID(user2);

            log.info("회원 탈퇴 완료");
            userRepository.delete(user);

    }
}
