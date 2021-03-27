package jp.co.kelly.biz.domain;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * SpringJunitConfigを使うと、SpringBootTestと異なって、必要なComponentのみをScanできるようになるので、テスト時間が短くなる。
 * ただ、特定のクラスだけをAutowiredしたい場合は、{@link SpringBootTests}のやり方がいいかも。
 */
@SpringJUnitConfig(SpringJunitConfigTest.Config.class)
class SpringJunitConfigTest {
    @Autowired
    DateConverter target;

    @ComponentScan(value = {"jp.co.kelly.biz.domain"}, nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
    static class Config {
    }

    @Test
    void test_01() {
        assertThat(target.now()).isAfter(LocalDateTime.of(2019, 8, 1, 1, 1));
    }
}
