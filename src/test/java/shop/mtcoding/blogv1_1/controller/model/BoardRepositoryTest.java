package shop.mtcoding.blogv1_1.controller.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1_1.dto.board.boardResp.BoardMainRespDto;
import shop.mtcoding.blogv1_1.model.BoardRepository;

@MybatisTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    ObjectMapper om;

    @Test
    public void findAllWithUser_test() throws Exception {
        // given
        om = new ObjectMapper();

        // when
        List<BoardMainRespDto> boardMainRespDto = boardRepository.findAllWithUsername();
        String responseBody = om.writeValueAsString(boardMainRespDto);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(boardMainRespDto.get(0).getContent()).isEqualTo("1번째 내용");

    }
}
