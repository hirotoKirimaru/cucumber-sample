package kirimaru;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.SimpleTransactionScope;

@Configuration
public class TransactionScopeConfig implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    beanFactory.registerScope("transaction", new SimpleTransactionScope());
  }
}
