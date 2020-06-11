package jp.co.kelly.biz.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssertXml {

    @Test
    void assertXml() {
        // language=xml
        String expect =
                "<script>" +
                        "     <java>12</java>    " +
                        "</script>    ";

        // language=xml
        String actual =
                "<script>" +
                        "<java>12</java>" +
                        "</script>";
        assertThat(actual).isXmlEqualTo(expect);
    }


}
