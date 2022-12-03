package kirimaru.external.mail;

import org.springframework.stereotype.Component;

@Component
public class 自社メールシステム implements MailFunction {

  @Override
  public void mail(String to, String from, String cc) {

  }
}
