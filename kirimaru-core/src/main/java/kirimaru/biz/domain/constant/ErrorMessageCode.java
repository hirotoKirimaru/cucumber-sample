package kirimaru.biz.domain.constant;

import static kirimaru.biz.domain.constant.LogLevel.ERROR;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorMessageCode {
  SYSTEM_ERROR("システムエラー", ERROR, false);


  private final String message;
  private final LogLevel logLevel;
  private final boolean logStackTrace;
}
