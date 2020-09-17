package com.example.cgv.service.User;

import com.example.cgv.domain.dto.User.LoginDto;
import com.example.cgv.domain.dto.User.SignInDto;
import com.example.cgv.domain.entity.User;
import com.example.cgv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service

@Slf4j
public class SignInService extends Exception{
    private final UserRepository userRepository;

    public SignInService(UserRepository userRepository){

        this.userRepository = userRepository;
    }


    public LoginDto SignIn(SignInDto signInDto) throws Exception {
        String user1=signInDto.getUserID();
        User user=userRepository.findByUserID(user1);

        if(user==null){
            log.info("존재하지 않는 아이디 입니다.");

            throw new Exception("존재하지 않는 아이디 입니다.");
        }

        if(!user.getPassword().equals(signInDto.getPassword())){
            log.info("비밀번호가 일치하지 않습니다.");
            System.out.println(user.getPassword());
            System.out.println(signInDto.getPassword());


            throw new Exception("비밀번호 일치 않습니다.");

        }


        log.info("로그인 성공");
        return new LoginDto(user.getUserID());
    }
}
