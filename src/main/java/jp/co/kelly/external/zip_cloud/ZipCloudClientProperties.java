package jp.co.kelly.external.zip_cloud;

import jp.co.kelly.external.operation.ExternalProperties;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external.zip-cloud")
@ToString(callSuper = true)
public class ZipCloudClientProperties extends ExternalProperties {
}
