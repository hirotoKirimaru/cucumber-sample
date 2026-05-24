package kirimaru.config;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.context.annotation.Bean;

public class FeatureFlags {

  @Bean
  public Unleash unleash() {
//    UnleashContextProvider contextProvider = new MyAwesomeContextProvider();
    UnleashConfig config = new UnleashConfig.Builder()
        .appName("java-test")
        .instanceId("instance x")
        .unleashAPI("http://unleash.herokuapp.com/api/")
//        .unleashContextProvider(contextProvider)
        .build();

    Unleash unleash = new DefaultUnleash(config);
    return unleash;
  }
}
