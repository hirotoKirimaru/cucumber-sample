package kirimaru.biz.domain;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * SpringJunitConfigを使うと、SpringBootTestと異なって、必要なComponentのみをScanできるようになるので、テスト時間が短くなる。
 * ただ、特定のクラスだけをAutowiredしたい場合は、
 */
@SpringBootTest(classes = DateConverter.class)
class SpringBootTests {
    @Autowired
    DateConverter target;

    @Test
    void test_01() {
        assertThat(target.now()).isAfter(LocalDateTime.of(2019, 8, 1, 1, 1));
    }
}
