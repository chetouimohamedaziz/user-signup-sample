package sample;

import org.springframework.stereotype.Service;

@Service
public class SignUpService {

  private final SignUpRepository signUpRepository;
  private final SmsApiClient smsApiClient;

  public SignUpService(SignUpRepository signUpRepository, SmsApiClient smsApiClient) {
    this.signUpRepository = signUpRepository;
    this.smsApiClient = smsApiClient;
  }

  public void signUp(SignUpRequest signUpRequest) {
    User user = saveUser(signUpRequest);
    smsApiClient.sendMessage(signUpRequest.phone, "Your verification code is " + user.getCode());
  }

  private User saveUser(SignUpRequest signUpRequest) {
    User user = new User(signUpRequest.login, signUpRequest.password, signUpRequest.phone);
    user.generateCode();
    signUpRepository.save(user);
    return user;
  }

}
