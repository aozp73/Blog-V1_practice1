package shop.mtcoding.blogv1_1.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1_1.dto.reply.ReplyResp.ReplyAllRespDto;

@MybatisTest
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    ObjectMapper om;

    @Test
    public void findByBoardIdwithUserTest() throws Exception {
        // given
        int id = 1;
        om = new ObjectMapper();

        // then
        List<ReplyAllRespDto> replyDtos = replyRepository.findByBoardIdwithUser(1);
        // String responseBody = om.writeValueAsString(replyDtos);
        // System.out.println("테스트 : " + responseBody);

        // when
        assertThat(replyDtos.get(0).getComment()).isEqualTo("댓글3");
    }
}
