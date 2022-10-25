package kirimaru.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "external.twitter2")
@ToString(callSuper = true)
public class Twitter2Properties extends ExternalProperties {
}
