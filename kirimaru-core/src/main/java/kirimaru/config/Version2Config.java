package kirimaru.config;

import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "info")
class Version2Config {

  Version version;

  @PostConstruct
  public void after() {
    System.out.println("Version2");
    System.out.println("***************");
    System.out.println(version);
  }
}
