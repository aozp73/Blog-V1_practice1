package shop.mtcoding.blogv1_1.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.board.boardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_1.dto.board.boardReq.BoardUpdateReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomApiException;
import shop.mtcoding.blogv1_1.model.Board;
import shop.mtcoding.blogv1_1.model.BoardRepository;
import shop.mtcoding.blogv1_1.util.HtmlParse;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void 게시글수정(BoardUpdateReqDto boardUpdateReqDto, int principalId) {
        // 게시물 존재 확인
        Board boardPS = boardRepository.findById(boardUpdateReqDto.getId());
        if (boardPS == null) {
            throw new CustomApiException("수정할 게시물이 존재하지 않습니다.");
        }

        // 게시물 userId, 로그인한 userId 체크
        if (boardPS.getUserId() != principalId) {
            throw new CustomApiException("수정 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        String thumbnail = HtmlParse.getThumbnail(boardUpdateReqDto.getContent());

        try {
            boardRepository.updateById(boardUpdateReqDto.getId(), boardUpdateReqDto.getTitle(),
                    boardUpdateReqDto.getContent(),
                    thumbnail);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void 게시글삭제(int boardId, int principalId) {
        // 게시물 존재 확인
        Board boardPS = boardRepository.findById(boardId);
        if (boardPS == null) {
            throw new CustomApiException("삭제할 게시물이 존재하지 않습니다.");
        }

        // 게시물 userId, 로그인한 userId 체크
        if (boardPS.getUserId() != principalId) {
            throw new CustomApiException("삭제 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        try {
            boardRepository.deleteById(boardId);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void 게시글작성(BoardSaveReqDto boardSaveReqDto, int principalId) {

        String thumbnail = HtmlParse.getThumbnail(boardSaveReqDto.getContent());

        try {
            boardRepository.insert(boardSaveReqDto.getTitle(), boardSaveReqDto.getContent(), thumbnail, principalId);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
