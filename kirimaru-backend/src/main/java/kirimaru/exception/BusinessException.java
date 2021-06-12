package kirimaru.exception;

import kirimaru.biz.domain.constant.ErrorMessageCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BusinessException extends RuntimeException {
  private final ErrorMessageCode errorMessageCode;
}
