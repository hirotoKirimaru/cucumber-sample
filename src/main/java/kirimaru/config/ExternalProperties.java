package kirimaru.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.net.URI;

@Getter
@Setter
@Slf4j
@ToString
public abstract class ExternalProperties {
  private String host;
  private String protocol;
  private String port;
  private String endpoint;
  private String timeout;

  public URI getUri() {
    return URI.create(protocol + "://" + host + ":" + port + "/" + endpoint);
  }

  @PostConstruct
  public void display() {
    log.info("********************");
    log.info(this.toString());
    log.info("********************");
  }
}
