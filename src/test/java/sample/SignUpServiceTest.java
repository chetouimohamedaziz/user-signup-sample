package sample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.eq;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SignUpServiceTest {

  private SignUpService signUpService;
  @Mock private SignUpRepository signUpRepository;
  @Mock private SmsApiClient smsApiClient;

  @Before
  public void setUp() throws Exception {
    signUpService = new SignUpService(signUpRepository, smsApiClient);
  }

  @Test
  public void signUp() {

    // given
    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.login = "user";
    signUpRequest.phone = "123";
    signUpRequest.password = "pwd";

    // when
    signUpService.signUp(signUpRequest);

    // then save to db
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    then(signUpRepository).should().save(userCaptor.capture());
    User user = userCaptor.getValue();
    assertThat(user).isEqualToIgnoringGivenFields(signUpRequest, "code");
    assertThat(user.getCode()).isNotZero();

    // and send sms
    then(smsApiClient).should().sendMessage(eq("123"), eq("Your verification code is " + user.getCode()));

  }

}