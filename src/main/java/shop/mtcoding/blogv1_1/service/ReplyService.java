package shop.mtcoding.blogv1_1.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.reply.ReplyReq.ReplyDetailReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomApiException;
import shop.mtcoding.blogv1_1.model.ReplyRepository;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void 게시글등록(ReplyDetailReqDto replyDetailReqDto, int id) {
        try {
            replyRepository.insert(replyDetailReqDto.getComment(), replyDetailReqDto.getBoardId(), id);
        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
