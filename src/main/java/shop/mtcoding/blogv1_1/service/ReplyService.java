package shop.mtcoding.blogv1_1.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.reply.ReplyReq.ReplyDetailReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomApiException;
import shop.mtcoding.blogv1_1.model.Reply;
import shop.mtcoding.blogv1_1.model.ReplyRepository;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void 댓글등록(ReplyDetailReqDto replyDetailReqDto, int id) {
        try {
            replyRepository.insert(replyDetailReqDto.getComment(), replyDetailReqDto.getBoardId(), id);
        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void 댓글삭제(int replyId, int principalId) {
        Reply replyPS = replyRepository.findById(replyId);
        if (replyPS == null) {
            throw new CustomApiException("삭제할 댓글이 존재하지 않습니다");
        }

        if (replyPS.getUserId() != principalId) {
            throw new CustomApiException("댓글 삭제 권한이 없습니다", HttpStatus.UNAUTHORIZED);
        }

        try {
            replyRepository.deleteById(replyId);
        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
