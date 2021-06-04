package kirimaru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Objects;

/**
 * Transaction
 */
@Configuration
public class TransactionConfig {

  /**
   * {@linkplain BeanFactoryTransactionAttributeSourceAdvisor}
   */
  @Bean
  public BeanFactoryTransactionAttributeSourceAdvisor transactionAdvisor(TransactionManager transactionManager) {
    var source = new NameMatchTransactionAttributeSource();
    source.addTransactionalMethod("*", new RuleBasedTransactionAttribute());

    var beanFactoryTransactionAttributeSourceAdvisor = new BeanFactoryTransactionAttributeSourceAdvisor();

    beanFactoryTransactionAttributeSourceAdvisor.setTransactionAttributeSource(source);
    beanFactoryTransactionAttributeSourceAdvisor.setAdvice(new TransactionInterceptor(transactionManager, source));
    beanFactoryTransactionAttributeSourceAdvisor.setClassFilter(
        clazz -> Objects.nonNull(AnnotationUtils.findAnnotation(clazz, Service.class))
    );

    return beanFactoryTransactionAttributeSourceAdvisor;
  }
}
