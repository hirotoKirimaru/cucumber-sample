package jp.co.kelly.biz.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssertJson {

    @Disabled("そんなものはない？")
    @Test
    void assertJson() {
        // language=json
        String expect = "{}";

        // language=json
        String actual = "{}";

        // こんなかんじでやりたい。
        assertThat(expect).isEqualTo(actual);
    }


}
