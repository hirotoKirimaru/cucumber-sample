package jp.co.kelly.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
@ConfigurationProperties(prefix = "external.twitter")
public class TwitterProperties extends ExternalProperties {
}
