package com.example.cgv.controller;

import com.example.cgv.domain.dto.User.*;
import com.example.cgv.domain.entity.User;
import com.example.cgv.repository.UserRepository;
import com.example.cgv.service.User.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;


@RestController
@RequestMapping("/movie/user")

public class UserController extends RestTemplate {
    private final UserRepository userRepository;
    private  final SignUpService signUpService;
    private final SignInService signInService;
    private final UserDeleteService userDeleteService;
    private final UserUpdateService userUpdateService;
    private final UserImforService userImforService;

    public UserController(UserRepository userRepository, SignUpService signUpService, SignInService signInService, UserDeleteService userDeleteService, UserUpdateService userUpdateService, UserImforService userImforService) {
        this.userRepository = userRepository;
        this.signUpService=signUpService;
        this.signInService=signInService;
        this.userDeleteService = userDeleteService;
        this.userUpdateService = userUpdateService;
        this.userImforService = userImforService;
    }
   //회원 가입
    @RequestMapping(value = "/sign", method = {RequestMethod.POST,RequestMethod.GET})
    public User SignUpUser(SignUpDto signUpDto)
    {
        return signUpService.signUp(signUpDto);
    }


    @ApiOperation(value = "회원가입")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "회원가입", value = "회원가입 정보", required = true, dataType = "")
    })

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpDto signUpDto) {

        return ResponseEntity.ok(signUpService.signUp(signUpDto));
    }


    /*
    @GetMapping("/signup")
    public ResponseEntity<User> SignUser(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(signUpService.signUp(signUpDto));
    }*/



    //로그인
 /*   @RequestMapping(value = "/signin", method = {RequestMethod.GET,RequestMethod.POST})
    public SignInDto SignInUser(SignInDto signInDto, HttpServletRequest response){
        return signInService.SignIn(signInDto);

    }*/

    @ApiOperation(value = "로그인")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "로그인", value = "로그인 정보", required = true, dataType = "")
    })
   // @GetMapping("/signIn")
    @PostMapping("/signIn")
    public ResponseEntity<LoginDto> signIn(@RequestBody SignInDto signInDto) throws Exception {

        return ResponseEntity.ok(signInService.SignIn(signInDto));
    }
/*    //알림입니다.
    @RequestMapping(value = "/memberJoin", method = {RequestMethod.POST,RequestMethod.GET})
    public void memberJoin() {
        logger.info("알림입니다.");
    }*/


    //회원탈퇴
    @RequestMapping(value = "/userDelete", method = {RequestMethod.POST,RequestMethod.GET})
    public void UserDelete(@CookieValue(value = "user", required = false)Cookie cookie){

        userDeleteService.UserDelete(cookie);
    }


    //사용자 id 중복 확인
    @RequestMapping(value = "/usercheck", method = {RequestMethod.POST,RequestMethod.GET})
    public Boolean userCheck(UserIdCheckDto userIdCheckDto){

        return signUpService.checkId(userIdCheckDto);
    }


    //회원 정보 수정
    @RequestMapping(value = "/userUpdate", method = {RequestMethod.POST,RequestMethod.GET})
    public void userUpdate(UserUpdateDto userUpdateDto){

        userUpdateService.UserUpdate(userUpdateDto);

    }


    //회원 정보 확인
 /*   @RequestMapping(value = "/userImfor", method = {RequestMethod.POST,RequestMethod.GET})
    public UserImforDto Userimfor(UserImforDto userImforDto){

       return  userImforService.UserImfor(userImforDto);
    }*/

    @ApiOperation(value = "회원정보 확인")
    @GetMapping("/UserImfor")
    public ResponseEntity<UserImforDto> userimfor(@CookieValue(value = "user", required = false)Cookie cookie) {
        return ResponseEntity.ok(userImforService.UserImfor(cookie));
    }

}

