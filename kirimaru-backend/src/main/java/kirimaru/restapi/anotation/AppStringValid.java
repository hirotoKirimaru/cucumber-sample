package kirimaru.restapi.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {AppStringValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppStringValid {
  String message() default "使用不可能な文字が含まれています。";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    AppStringValid[] value();
  }
}
