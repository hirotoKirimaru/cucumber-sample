package kirimaru.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@Getter
@Setter
@ConfigurationProperties(prefix = "external.facebook")
@ToString(callSuper = true)
public class FacebookProperties extends ExternalProperties {
}
