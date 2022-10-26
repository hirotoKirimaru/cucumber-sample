package kirimaru.restapi.anotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import kirimaru.biz.domain.AppString;

public class PrintableStringValidator implements ConstraintValidator<PrintableStringValid, String> {

  @Override
  public void initialize(PrintableStringValid constraintAnnotation) {
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
