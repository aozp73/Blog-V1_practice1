package shop.mtcoding.blogv1_1.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1_1.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void login_test() throws Exception {
        // given
        String username = "ssar";
        String password = "1234";

        String requestBody = "username=" + username + "&password=" + password;

        // when
        ResultActions resultActions = mvc.perform(post("/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        User user = (User) session.getAttribute("principal");
        // System.out.println("테스트 : " + user.getUsername());

        // then
        resultActions.andExpect(status().is3xxRedirection());
        assertThat(user.getUsername()).isEqualTo("ssar");
    }

    @Test
    public void join_test() throws Exception {
        // given
        String username = "cos";
        String password = "1234";
        String email = "cos@nate.com";

        String requestBody = "username=" + username + "&password=" + password +
                "&email=" + email;
        // System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/join")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }
}
