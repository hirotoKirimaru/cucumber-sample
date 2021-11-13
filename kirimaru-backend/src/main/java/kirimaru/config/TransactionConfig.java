package kirimaru.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor;

/**
 * Transaction
 */
@Configuration
public class TransactionConfig {

  /**
   * {@linkplain BeanFactoryTransactionAttributeSourceAdvisor}
   */
  // 依存関係が足りない？
//  @Bean
//  public BeanFactoryTransactionAttributeSourceAdvisor transactionAdvisor(TransactionManager transactionManager) {
//
//    var source = new NameMatchTransactionAttributeSource();
//    source.addTransactionalMethod("*", new RuleBasedTransactionAttribute());
//
//    var beanFactoryTransactionAttributeSourceAdvisor = new BeanFactoryTransactionAttributeSourceAdvisor();
//
//    beanFactoryTransactionAttributeSourceAdvisor.setTransactionAttributeSource(source);
//    beanFactoryTransactionAttributeSourceAdvisor.setAdvice(new TransactionInterceptor(transactionManager, source));
//    beanFactoryTransactionAttributeSourceAdvisor.setClassFilter(
//        clazz -> Objects.nonNull(AnnotationUtils.findAnnotation(clazz, Service.class))
//    );
//
//    return beanFactoryTransactionAttributeSourceAdvisor;
//  }
}
