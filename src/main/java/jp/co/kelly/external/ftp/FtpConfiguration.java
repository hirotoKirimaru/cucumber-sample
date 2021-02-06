package jp.co.kelly.external.ftp;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ftp")
@ToString(callSuper = true)
@Data
public class FtpConfiguration {
  private String username;
  private String password;
  private String host;
  private int port;
  private int defaultTimeout;
  private int soTimeout;
  private int dataTimeout;


}
