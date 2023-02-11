package shop.mtcoding.blogv1_1.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardReq {

    @Getter
    @Setter
    public static class BoardSaveReqDto {
        private String title;
        private String content;
    }

    @Getter
    @Setter
    public static class BoardUpdateReqDto {
        private int id;
        private String title;
        private String content;
    }
}
