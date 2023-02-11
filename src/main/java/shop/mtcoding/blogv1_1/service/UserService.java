package shop.mtcoding.blogv1_1.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.blogv1_1.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomException;
import shop.mtcoding.blogv1_1.model.User;
import shop.mtcoding.blogv1_1.model.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void 회원가입(UserJoinReqDto userJoinReqDto) {
        // DB - 아이디 존재 확인
        User user = userRepository.findByUsername(userJoinReqDto.getUsername());
        if (user != null) {
            throw new CustomException("동일한 아이디가 존재합니다.");
        }

    }

    public User 로그인(UserLoginReqDto userloginReqDto) {
        return new User();
    }
}
