package shop.mtcoding.blogv1_1.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyReq {

    @Getter
    @Setter
    public static class ReplyDetailReqDto {
        private String comment;
        private int boardId;
    }
}
