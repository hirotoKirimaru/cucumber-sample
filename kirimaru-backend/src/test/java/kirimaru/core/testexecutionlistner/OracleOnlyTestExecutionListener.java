package kirimaru.core.testexecutionlistner;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import kirimaru.core.anotation.OracleOnly;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class OracleOnlyTestExecutionListener implements TestExecutionListener {

  @Override
  public void beforeTestClass(TestContext testContext) throws Exception {
    var annotation = AnnotationUtils.findAnnotation(testContext.getTestClass(), OracleOnly.class);
    if (isNotTarget(annotation, testContext)) {
      return;
    }
    assumeTrue(
        false,
        () -> String.format("%s#はOracleでのみ動作します",
            testContext.getTestClass().getSimpleName())
    );
  }


  @Override
  public void beforeTestMethod(TestContext testContext) {
    var annotation = AnnotationUtils.findAnnotation(testContext.getTestMethod(), OracleOnly.class);
    if (isNotTarget(annotation, testContext)) {
      return;
    }

    assumeTrue(
        false,
        () -> String.format("%s#%sはOracleでのみ動作します",
            testContext.getTestClass().getSimpleName(), testContext.getTestMethod().getName())
    );

  }

  private boolean isNotTarget(OracleOnly annotation, TestContext testContext) {
    if (annotation == null) {
      return true;
    }

    String property = testContext.getApplicationContext().getEnvironment()
        .getProperty("spring.dummy-datasource.driver-class-name");
    return "oracle.jdbc.OracleDriver".equals(property);
  }
}
