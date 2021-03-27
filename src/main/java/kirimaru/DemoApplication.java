package kirimaru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(scanBasePackages = {"jp.co.kelly"})
@ComponentScan(basePackages = "jp.co.kelly", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
