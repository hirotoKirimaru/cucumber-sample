package kirimaru.restapi.anotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
