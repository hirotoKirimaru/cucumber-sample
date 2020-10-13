package jp.co.kelly.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
public abstract class ExternalProperties {
  private String host;
  private String protocol;
  private String port;
  private String endpoint;
  private String timeout;


  public URL getUrl(){
    try {
      return new URL("");
    } catch (MalformedURLException e) {
      throw new RuntimeException("URLじゃないよ！");
    }
  }
}
