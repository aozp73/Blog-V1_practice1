package shop.mtcoding.blogv1_1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1_1.dto.reply.ReplyReq.ReplyDetailReqDto;
import shop.mtcoding.blogv1_1.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ReplyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper om;

    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;

        // then
        ResultActions resultActions = mvc.perform(delete("/reply/" + id)
                .session(mockSession));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("????????? : " + responseBody);

        // when
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.msg").value("?????? ????????????"));
    }

    @Test
    public void save_test() throws Exception {
        // given
        ReplyDetailReqDto replyDetailReqDto = new ReplyDetailReqDto();
        replyDetailReqDto.setBoardId(1);
        replyDetailReqDto.setComment("???????????????");
        ;
        String requestBody = om.writeValueAsString(replyDetailReqDto);

        // then
        ResultActions resultActions = mvc.perform(put("/reply")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .session(mockSession));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("????????? : " + responseBody);

        // when
        resultActions.andExpect(status().isCreated());
    }
}
