package shop.mtcoding.blogv1_1.dto.board;

import lombok.Getter;
import lombok.Setter;

public class boardReq {

    @Getter
    @Setter
    public static class BoardSaveReqDto {
        private String title;
        private String content;
    }
}
