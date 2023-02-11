package shop.mtcoding.blogv1_1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.ResponseDto;
import shop.mtcoding.blogv1_1.dto.reply.ReplyReq.ReplyDetailReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomApiException;
import shop.mtcoding.blogv1_1.model.User;
import shop.mtcoding.blogv1_1.service.ReplyService;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final HttpSession session;

    private final ReplyService replyService;

    @DeleteMapping("/reply/{id}") // 유효성 검사(x), 인증 o, 권한 o
    public ResponseEntity<?> delete(@PathVariable int id) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("로그인이 필요합니다");
        }

        replyService.댓글삭제(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "댓글 삭제완료", null), HttpStatus.OK);
    }

    @PutMapping("/reply")
    public ResponseEntity<?> reply(@RequestBody ReplyDetailReqDto replyDetailReqDto) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("로그인이 필요합니다");
        }

        // 유효성 검사
        if (replyDetailReqDto.getComment() == null || replyDetailReqDto.getComment().isEmpty()) {
            throw new CustomApiException("댓글을 입력하세요");
        }

        replyService.댓글등록(replyDetailReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "댓글을 작성하였습니다", null), HttpStatus.CREATED);
    }
}
