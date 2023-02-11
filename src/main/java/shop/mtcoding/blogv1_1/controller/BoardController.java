package shop.mtcoding.blogv1_1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.board.boardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomException;
import shop.mtcoding.blogv1_1.model.BoardRepository;
import shop.mtcoding.blogv1_1.model.User;
import shop.mtcoding.blogv1_1.service.BoardService;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @PostMapping("/board") // 유효성 검사(Post), 인증 o, 권한 x
    public String save(BoardSaveReqDto boardSaveReqDto) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("로그인이 필요합니다");
        }

        // 유효성 검사
        if (boardSaveReqDto.getTitle() == null || boardSaveReqDto.getTitle().isEmpty()) {
            throw new CustomException("제목을 입력하세요");
        }
        if (boardSaveReqDto.getContent() == null || boardSaveReqDto.getContent().isEmpty()) {
            throw new CustomException("내용을 입력하세요");
        }
        if (boardSaveReqDto.getTitle().length() > 100) {
            throw new CustomException("제목은 100자 이하여야 합니다");
        }

        boardService.게시글작성(boardSaveReqDto, principal.getId());

        return "redirect:/";
    }

    @GetMapping({ "/", "/main" })
    public String main(Model model) {
        model.addAttribute("principal", boardRepository.findAllWithUsername());
        return "/board/main";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "/board/saveForm";
    }
}
