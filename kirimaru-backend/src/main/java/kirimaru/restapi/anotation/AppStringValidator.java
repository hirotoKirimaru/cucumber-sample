package kirimaru.restapi.anotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kirimaru.biz.domain.AppString;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;

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
    var annotationDescriptor = ((ConstraintDescriptorImpl) ((ConstraintValidatorContextImpl) context).getConstraintDescriptor()).getAnnotationDescriptor();
    int byteCount = (Integer) annotationDescriptor.getAttribute("byteCount");
    int wordCount = (Integer) annotationDescriptor.getAttribute("wordCount");

    AppString appString = AppString.of(value);
    boolean byteCountResult = byteCount != 0 && appString.illegalByteCount(byteCount);
    boolean wordCountResult = wordCount != 0 && appString.illegalWordCount(wordCount);
    return byteCountResult || wordCountResult;
  }
}
