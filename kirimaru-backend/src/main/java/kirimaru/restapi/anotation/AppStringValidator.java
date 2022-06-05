package kirimaru.restapi.anotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import kirimaru.biz.domain.AppString;

public class AppStringValidator implements ConstraintValidator<AppStringValid, String> {


  @Override
  public void initialize(AppStringValid constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    return AppString.isIllegal(value);
  }
}
