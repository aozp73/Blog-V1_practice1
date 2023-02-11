package shop.mtcoding.blogv1_1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.ResponseDto;
import shop.mtcoding.blogv1_1.dto.board.boardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomApiException;
import shop.mtcoding.blogv1_1.model.BoardRepository;
import shop.mtcoding.blogv1_1.model.User;
import shop.mtcoding.blogv1_1.service.BoardService;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @DeleteMapping("/board/{id}") // 유효성 검사(x), 인증 o, 권한 o
    public ResponseEntity<?> delete(@PathVariable int id) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("로그인이 필요합니다");
        }

        boardService.게시글삭제(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 삭제완료", null), HttpStatus.OK);
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("dto", boardRepository.findByBoardIdWithUser(id));
        System.out.println("테스트 : " + id);
        return "board/detail";
    }

    @PostMapping("/board") // 유효성 검사(Post), 인증 o, 권한 x
    public ResponseEntity<?> save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("로그인이 필요합니다");
        }

        // 유효성 검사
        if (boardSaveReqDto.getTitle() == null || boardSaveReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("제목을 입력하세요");
        }
        if (boardSaveReqDto.getContent() == null || boardSaveReqDto.getContent().isEmpty()) {
            throw new CustomApiException("내용을 입력하세요");
        }
        if (boardSaveReqDto.getTitle().length() > 100) {
            throw new CustomApiException("제목은 100자 이하여야 합니다");
        }

        boardService.게시글작성(boardSaveReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 등록완료", null), HttpStatus.CREATED);
    }

    @GetMapping({ "/", "/main" })
    public String main(Model model) {
        model.addAttribute("dtos", boardRepository.findAllWithUsername());
        return "/board/main";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "/board/saveForm";
    }
}
