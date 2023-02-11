package shop.mtcoding.blogv1_1.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_1.dto.board.boardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_1.handler.ex.CustomException;
import shop.mtcoding.blogv1_1.model.BoardRepository;
import shop.mtcoding.blogv1_1.util.HtmlParse;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void 게시글작성(BoardSaveReqDto boardSaveReqDto, int principalId) {

        String thumbnail = HtmlParse.getThumbnail(boardSaveReqDto.getContent());

        try {
            boardRepository.insert(boardSaveReqDto.getTitle(), boardSaveReqDto.getContent(), thumbnail, principalId);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
