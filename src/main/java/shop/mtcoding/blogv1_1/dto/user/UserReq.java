package shop.mtcoding.blogv1_1.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserReq {

    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserJoinReqDto {
        private String username;
        private String password;
        private String email;
    }
}
