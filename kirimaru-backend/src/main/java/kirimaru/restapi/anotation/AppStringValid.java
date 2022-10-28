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
  String message() default "文字数が規定より超えています。";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  int byteCount() default 0;
  int wordCount() default 0;

  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    AppStringValid[] value();
  }
}
