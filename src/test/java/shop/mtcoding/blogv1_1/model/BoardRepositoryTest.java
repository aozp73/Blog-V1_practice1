package shop.mtcoding.blogv1_1.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1_1.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.blogv1_1.dto.board.BoardResp.BoardMainRespDto;

@MybatisTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    ObjectMapper om;

    @Test
    public void findByBoardIdWithUser_test() throws Exception {
        // given
        om = new ObjectMapper();

        // when
        BoardDetailRespDto boardDetailRespDto = boardRepository.findByBoardIdWithUser(1);

        // then
        assertThat(boardDetailRespDto.getContent()).isEqualTo("1번째 내용");
    }

    @Test
    public void findAllWithUser_test() throws Exception {
        // given
        om = new ObjectMapper();

        // when
        List<BoardMainRespDto> boardMainRespDto = boardRepository.findAllWithUsername();
        // String responseBody = om.writeValueAsString(boardMainRespDto);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(boardMainRespDto.get(0).getContent()).isEqualTo("1번째 내용");
        assertThat(boardMainRespDto.get(0).getUserId()).isEqualTo(1);
    }
}
