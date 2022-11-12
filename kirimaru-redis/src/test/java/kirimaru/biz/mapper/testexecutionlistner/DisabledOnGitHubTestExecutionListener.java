package kirimaru.biz.mapper.testexecutionlistner;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import kirimaru.biz.mapper.anotation.DisabledOnGitHub;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class DisabledOnGitHubTestExecutionListener implements TestExecutionListener {

  @Override
  public void beforeTestClass(TestContext testContext) throws Exception {
    var annotation = AnnotationUtils.findAnnotation(testContext.getTestClass(), DisabledOnGitHub.class);
    if (isNotTarget(annotation, testContext)) {
      return;
    }
    assumeTrue(
        false,
        () -> String.format("%sはGitHubでは動きません",
            testContext.getTestClass().getSimpleName())
    );
  }


  @Override
  public void beforeTestMethod(TestContext testContext) {
    var annotation = AnnotationUtils.findAnnotation(testContext.getTestMethod(), DisabledOnGitHub.class);
    if (isNotTarget(annotation, testContext)) {
      return;
    }

    assumeTrue(
        false,
        () -> String.format("%s#%sはGitHubでは動きません",
            testContext.getTestClass().getSimpleName(), testContext.getTestMethod().getName())
    );

  }

  private boolean isNotTarget(DisabledOnGitHub annotation, TestContext testContext) {
    if (annotation == null) {
      return true;
    }

    String property = testContext.getApplicationContext().getEnvironment()
        .getProperty("GITHUB");
    String property2 = System.getProperty("GITHUB");
    System.out.println("*************");
    System.out.println(property);
    System.out.println(property2);

    return !Boolean.parseBoolean(property);
  }
}
