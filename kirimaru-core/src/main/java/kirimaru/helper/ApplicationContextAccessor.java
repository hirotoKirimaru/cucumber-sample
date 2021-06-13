package kirimaru.helper;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.Environment;

public final class ApplicationContextAccessor {
  static BeanFactory beanFactory;
  static Environment environment;

  public static void initialize(ConfigurableListableBeanFactory factory, Environment env) {
    beanFactory = factory;
    environment = env;
  }

  public static String getProperty(String key) {
    return environment.getProperty(key);
  }

  public static <T> T getBean(Class<T> clazz) {
    return beanFactory.getBean(clazz);
  }
}
