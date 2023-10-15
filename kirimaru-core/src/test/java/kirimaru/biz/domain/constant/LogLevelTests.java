package kirimaru.biz.domain.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;


@ExtendWith(MockitoExtension.class)
class LogLevelTests {
  @Mock
  private Logger logger;

  @Nested
  class NoneTest {
    @Test
    void testIsLogEnabled() {
      var actual = LogLevel.NONE.isLogEnabled(logger);
      assertThat(actual).isFalse();
      Mockito.verifyNoInteractions(logger);
    }
  }

  @Nested
  class TraceTest {
    @Test
    void testIsLogEnabled() {
      when(logger.isTraceEnabled()).thenReturn(true);

      var actual = LogLevel.TRACE.isLogEnabled(logger);

      assertThat(actual).isTrue();
      Mockito.verify(logger).isTraceEnabled();
      Mockito.verifyNoMoreInteractions(logger);
    }
  }

}
