package sample;


import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(SignUpController.class)
public class SignUpControllerTests {

  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean SignUpService signUpService;

  @Test public void signUp() throws Exception {
    // given
    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.login = "user";
    signUpRequest.password = "pwd";
    signUpRequest.phone = "123";

    // when
    ResultActions result = mockMvc.perform(
        post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signUpRequest))
    );

    // then
    result.andExpect(status().isOk());
    // and
    then(signUpService).should().signUp(any());
  }

}
