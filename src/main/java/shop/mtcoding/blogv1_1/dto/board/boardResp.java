package shop.mtcoding.blogv1_1.dto.board;

import lombok.Getter;
import lombok.Setter;

public class boardResp {

    @Getter
    @Setter
    public static class BoardMainRespDto {
        private int BoardId;
        private String title;
        private String content;
        private String thumbnail;
        private int userId;
        private String username;
    }

    @Getter
    @Setter
    public static class BoardDetailRespDto {
        private int BoardId;
        private String title;
        private String content;
        private int userId;
        private String username;
    }

}
