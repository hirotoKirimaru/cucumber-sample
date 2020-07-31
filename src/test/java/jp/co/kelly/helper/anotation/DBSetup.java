package jp.co.kelly.helper.anotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DBSetup {
  String path();
}
