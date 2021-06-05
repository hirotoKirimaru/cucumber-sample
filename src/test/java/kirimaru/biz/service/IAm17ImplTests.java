package kirimaru.biz.service;

import kirimaru.biz.domain.date.SystemDateTimeResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IAm17ImplTests {
  @Mock
  SystemDateTimeResolver systemDateTimeResolver;
  @InjectMocks
  IAm17Impl target;

  @Test
  void test_01() {
    assertThat(target.iam17(LocalDateTime.now()))
        .isEqualTo("");
    ;
  }
}
