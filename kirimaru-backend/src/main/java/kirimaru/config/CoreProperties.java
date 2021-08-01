package kirimaru.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.config")
public class CoreProperties {
  private String appName;
  private String local;
}
