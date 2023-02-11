package shop.mtcoding.blogv1_1.service;

import org.springframework.http.HttpStatus;
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
        try {
            userRepository.insert(userJoinReqDto.getUsername(), userJoinReqDto.getPassword(),
                    userJoinReqDto.getEmail());
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public User 로그인(UserLoginReqDto userloginReqDto) {
        // username 존재여부
        User user = userRepository.findByUsername(userloginReqDto.getUsername());
        if (user == null) {
            throw new CustomException("일치하는 아이디가 없습니다");
        }
        // password 일치여부
        if (!user.getPassword().equals(userloginReqDto.getPassword())) {
            throw new CustomException("패스워드가 일치하지 않습니다");
        }

        return user;
    }
}
