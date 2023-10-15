package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EntryTest {

  @Test
  void test_01() {
    Entry entry = new Entry();

    assertThat(entry.flatHistory()).isEqualTo(List.of());
  }

  @Test
  void test_02() {
    var A1 = ReturnHistory.builder().build();
    var B1 = ReturnHistory.builder().build();

    var A = Detail.builder().returnHistory(List.of(A1)).build();
    var B = Detail.builder().returnHistory(List.of(B1)).build();
    Entry entry = new Entry(
        List.of(
            A, B
        )
    );

    assertThat(entry.flatHistory()).isEqualTo(List.of(A1, B1));
  }
}
