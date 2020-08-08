package jp.co.kelly.biz.domain;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
