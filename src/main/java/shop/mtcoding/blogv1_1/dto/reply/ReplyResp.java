package shop.mtcoding.blogv1_1.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyResp {

    @Getter
    @Setter
    public static class ReplyAllRespDto {
        private int replyId;
        private String comment;
        private int userId;
        private int boardId;
        private String username;
    }
}
