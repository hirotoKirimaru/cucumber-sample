package kirimaru.external.mail;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AwsSes implements MailFunction{
}
