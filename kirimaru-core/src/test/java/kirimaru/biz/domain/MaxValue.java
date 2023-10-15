package kirimaru.biz.domain;

import java.util.Arrays;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaxValue {
    SoftAssertions softly;

    @BeforeEach
    void setUp() {
        this.softly = new SoftAssertions();
    }

    @AfterEach
    void tearDown() {
        softly.assertAll();
    }

    @Test
    void _assertThatThrownBy() {
        String[] inputList = {"30", "70", "100"};

        int actual = Arrays.stream(inputList)
                .mapToInt(Integer::parseInt).max().getAsInt();

        softly.assertThat(actual).isEqualTo(100);
    }
}
