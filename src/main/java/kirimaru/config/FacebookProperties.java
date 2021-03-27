package kirimaru.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "external.facebook")
public class FacebookProperties extends ExternalProperties {
}
