package kirimaru.helper;

import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationContextAccessorConfig {
  @Bean
  public static BeanFactoryPostProcessor beanAccessorInitializer() {
    return new ApplicationContextAccessorInitializer();
  }

  private static class ApplicationContextAccessorInitializer
      implements BeanFactoryPostProcessor, EnvironmentAware {
    @Setter
    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
      ApplicationContextAccessor.initialize(beanFactory, environment);
    }

  }
}
