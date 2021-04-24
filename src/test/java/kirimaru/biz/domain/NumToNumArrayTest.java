package kirimaru.biz.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class NumToNumArrayTest {
  NumToNumArray numToNumArray = new NumToNumArray();

  @Test
  void test_01() {
    assertThat(
        numToNumArray.toArrayNoOrder(109)
    ).isEqualTo(new int[]{});
  }
}
