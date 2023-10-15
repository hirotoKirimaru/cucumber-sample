package kirimaru.biz.domain;

import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import kirimaru.biz.domain.date.OffsetDateTimeResolver;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("""
初回は正常に動くが、2回目以降にエラーとなる方法の確認
"""
)
@ExtendWith(MockitoExtension.class)
class FirstSuccessSecondErrorTests {

  public static final String ERROR_MESSAGE = "aiueo";

  @Mock
  private OffsetDateTimeResolver offsetDateTimeResolver;

  @Test
  void normal() {
    OffsetDateTime now = OffsetDateTime.now();
    when(offsetDateTimeResolver.now()).thenReturn(now);

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertAll();
  }

  @DisplayName("1回目OK,2回目以降NG")
  @Test
  void first_success_second_error() {
    // GIVEN
    OffsetDateTime now = OffsetDateTime.now();
    when(offsetDateTimeResolver.now())
        .thenReturn(now)
        .thenThrow(new RuntimeException(ERROR_MESSAGE));

    // WHEN
    // THEN
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThatThrownBy(() -> offsetDateTimeResolver.now())
        .isInstanceOfSatisfying(RuntimeException.class,
            (e) -> softly.assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE));
    softly.assertAll();
  }

  @DisplayName("1回目OK, 2回目OK, 3回目以降NG")
  @Test
  void first_success_second_success_third_success() {
    // GIVEN
    OffsetDateTime now = OffsetDateTime.now();
    when(offsetDateTimeResolver.now())
        .thenReturn(now)
        .thenReturn(now)
        .thenThrow(new RuntimeException(ERROR_MESSAGE));

    // WHEN
    // THEN
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThatThrownBy(() -> offsetDateTimeResolver.now())
        .isInstanceOfSatisfying(RuntimeException.class,
            (e) -> softly.assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE));
    softly.assertAll();
  }

  @DisplayName("1回目OK, 2回目NG, 3回目OK, 4回目NG")
  @Test
  void first_success_second_error_third_success_forth_error() {
    // GIVEN
    OffsetDateTime now = OffsetDateTime.now();
    when(offsetDateTimeResolver.now())
        .thenReturn(now)
        .thenThrow(new RuntimeException(ERROR_MESSAGE))
        .thenReturn(now)
        .thenThrow(new RuntimeException(ERROR_MESSAGE));

    // WHEN
    // THEN
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThatThrownBy(() -> offsetDateTimeResolver.now())
        .isInstanceOfSatisfying(RuntimeException.class,
            (e) -> softly.assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE));
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThatThrownBy(() -> offsetDateTimeResolver.now())
        .isInstanceOfSatisfying(RuntimeException.class,
            (e) -> softly.assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE));
    softly.assertAll();
  }

  @Test
  void _1OK_2OK_3OK_4NG_5OK_6NG() {
    // GIVEN
    OffsetDateTime now = OffsetDateTime.now();
    when(offsetDateTimeResolver.now())
        .thenReturn(now, now, now)
        .thenThrow(new RuntimeException(ERROR_MESSAGE))
        .thenReturn(now)
        .thenThrow(new RuntimeException(ERROR_MESSAGE));

    // WHEN
    // THEN
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThatThrownBy(() -> offsetDateTimeResolver.now())
        .isInstanceOfSatisfying(RuntimeException.class,
            (e) -> softly.assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE));
    softly.assertThat(offsetDateTimeResolver.now()).isEqualTo(now);
    softly.assertThatThrownBy(() -> offsetDateTimeResolver.now())
        .isInstanceOfSatisfying(RuntimeException.class,
            (e) -> softly.assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE));
    softly.assertAll();
  }

  @DisplayName("Mockitoの設定方法の変更")
  @Test
  void setting() {
    // GIVEN
    OffsetDateTime now = OffsetDateTime.now();
    when(offsetDateTimeResolver.now()).thenReturn(now);
    when(offsetDateTimeResolver.now()).thenThrow(new RuntimeException(ERROR_MESSAGE));

    // WHEN
    // THEN
    SoftAssertions softly = new SoftAssertions();
    softly.assertThatThrownBy(() -> offsetDateTimeResolver.now())
        .isInstanceOfSatisfying(RuntimeException.class,
            (e) -> softly.assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE));
    softly.assertThatThrownBy(() -> offsetDateTimeResolver.now())
        .isInstanceOfSatisfying(RuntimeException.class,
            (e) -> softly.assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE));
    softly.assertAll();
  }
}