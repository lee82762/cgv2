package com.example.cgv.service.User;

import com.example.cgv.domain.dto.User.SignUpDto;
import com.example.cgv.domain.dto.User.UserIdCheckDto;
import com.example.cgv.domain.entity.User;
import com.example.cgv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SignUpService {
    private final UserRepository userRepository;
    public SignUpService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }


    public User signUp(SignUpDto signUpDto) {

        User user = signUpDto.toEntity();

        System.out.println(user.getAddress());

        return userRepository.save(user);
    }


    //아이디 중복 체크
    public boolean checkId(UserIdCheckDto userIdCheckDto){
        User user=userRepository.findByUserID(userIdCheckDto.getUserID());
        if(user!=null){
            log.info("이미 있는 아이디");
        }
        else {
            log.info("중복 체크완료(사용가능한 아이디)");
        }
        return true;
    }


}
