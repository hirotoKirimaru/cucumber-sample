package kirimaru.helper.listeners;

import java.util.Objects;
import kirimaru.helper.anotation.DBSetup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

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
