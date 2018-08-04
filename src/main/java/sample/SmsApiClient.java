package sample;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SmsApiClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(SmsApiClient.class);

  private final String apiKey;
  private final String apiSecret;
  private final String from;
  private final NexmoClient client;

  public SmsApiClient(@Value("${sms.api_key}") String apiKey,
                      @Value("${sms.api_secret}") String apiSecret,
                      @Value("${sms.from}") String from) {

    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    this.from = from;

    AuthMethod auth = new TokenAuthMethod(apiKey, apiSecret);
    client = new NexmoClient(auth);
  }


  public void sendMessage(String toNumber, String text) {

    SmsSubmissionResult[] responses = new SmsSubmissionResult[0];
    try {
      responses = client.getSmsClient().submitMessage(new TextMessage(
          from,
          toNumber,
          text));
    } catch (IOException | NexmoClientException e) {
      LOGGER.error("message not sent", e);
    }
    for (SmsSubmissionResult response : responses) {
      LOGGER.info(response.toString());
    }
  }

}
