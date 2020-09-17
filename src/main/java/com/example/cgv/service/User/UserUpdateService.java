package com.example.cgv.service.User;

import com.example.cgv.domain.dto.User.UserUpdateDto;
import com.example.cgv.domain.entity.User;
import com.example.cgv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserUpdateService {
    private final UserRepository userRepository;

    public UserUpdateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void UserUpdate(UserUpdateDto userUpdateDto){
        String user1=userUpdateDto.getUserID();
        //User UserUpdate1로 바꾸끼
        User user=userRepository.findByUserID("cjdrn");

        if(user==null){

            log.info("사용자 이름을 찾을 수 없습니다.");
        }
        user.UserUpdate1(userUpdateDto);

        userRepository.save(user);

    }
}
