package kirimaru.restapi.interceptor;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class LoggingInterceptorTests {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HandlerMethod handlerMethod;
  @Mock
  Appender<ILoggingEvent> appender;
  @Captor
  ArgumentCaptor<ILoggingEvent> captor;

  @BeforeEach
  void setUpAppender(){

    ch.qos.logback.classic.Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    logger.addAppender(appender);
  }


  @Test
  void preHandle1() throws Exception {


  }
}
