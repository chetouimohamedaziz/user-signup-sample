package sample;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {

  private final SignUpService signUpService;

  public SignUpController(SignUpService signUpService) {
    this.signUpService = signUpService;
  }

  @PostMapping
  public void signUp(@RequestBody SignUpRequest signUpRequest) {
    signUpService.signUp(signUpRequest);
  }

}
