package jp.co.kelly.config;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
public abstract class ExternalProperties {
  private String host;
  private String protocol;
  private String port;
  private String endpoint;
  private String timeout;


  public URI getUri() {
    return URI.create(protocol + "://" + host + ":" + port + "/" + endpoint);
  }
}
