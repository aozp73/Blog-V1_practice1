package shop.mtcoding.blogv1_1.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.blogv1_1.model.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void 회원가입(UserJoinReqDto userJoinReqDto) {

    }
}
