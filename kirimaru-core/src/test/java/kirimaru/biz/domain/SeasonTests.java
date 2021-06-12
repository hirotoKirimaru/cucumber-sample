package kirimaru.biz.domain;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class SeasonTests {
    TableStand target = new TableStand();


    @Test
    void test_01() {
        Season season = target.transSeason("夏");

        AssertionsForClassTypes.assertThat(season).isEqualTo(Season.SUMMER);

    }

    @Test
    void test_02() {
        target.disp("夏");
    }
}
