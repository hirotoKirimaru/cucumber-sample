package jp.co.kelly.helper.listeners;

import jp.co.kelly.helper.anotation.DBSetup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

@Slf4j
public class DBSetupListener implements TestExecutionListener {
  @Override
  public void beforeTestMethod(TestContext testContext) throws Exception {
    DBSetup annotation = AnnotationUtils.findAnnotation(testContext.getTestMethod(), DBSetup.class);

    if (Objects.isNull(annotation)) {
      return;
    }
    log.info("ゴリラ");
    log.info(annotation.path());
  }
}
