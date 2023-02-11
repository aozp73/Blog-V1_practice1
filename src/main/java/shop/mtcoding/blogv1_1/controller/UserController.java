package shop.mtcoding.blogv1_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.blogv1_1.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomException;
import shop.mtcoding.blogv1_1.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/login") // 유효성 검사(Post), 인증 x, 권한 x
    public String login(UserLoginReqDto userloginReqDto) {
        // 유효성 검사
        if (userloginReqDto.getUsername() == null || userloginReqDto.getUsername().isEmpty()) {
            throw new CustomException("아이디를 입력하세요");
        }
        if (userloginReqDto.getPassword() == null || userloginReqDto.getPassword().isEmpty()) {
            throw new CustomException("패스워드를 입력하세요");
        }

        userService.로그인(userloginReqDto);

        return "redirect:/";
    }

    @PostMapping("/join") // 유효성 검사(Post), 인증 x, 권한 x
    public String join(UserJoinReqDto userJoinReqDto) {
        // 유효성 검사
        if (userJoinReqDto.getUsername() == null || userJoinReqDto.getUsername().isEmpty()) {
            throw new CustomException("아이디를 입력하세요");
        }
        if (userJoinReqDto.getPassword() == null || userJoinReqDto.getPassword().isEmpty()) {
            throw new CustomException("패스워드를 입력하세요");
        }
        if (userJoinReqDto.getEmail() == null || userJoinReqDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 입력하세요");
        }

        userService.회원가입(userJoinReqDto);

        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }
}
