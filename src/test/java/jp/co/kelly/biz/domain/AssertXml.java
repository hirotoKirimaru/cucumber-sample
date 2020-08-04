package jp.co.kelly.biz.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.placeholder.PlaceholderDifferenceEvaluator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

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
        // ファイルがある場合。
//        isXmlEqualToContentOf
    }

    @Test
    void assertXml2() {
        // 使用ライブラリのテスト
        // https://github.com/xmlunit/xmlunit/blob/master/xmlunit-placeholders/src/test/java/org/xmlunit/placeholder/PlaceholderDifferenceEvaluatorTest.java

        // wiremockも内部的にはこれを使っている。
        //     testImplementation 'org.xmlunit:xmlunit-placeholders:2.7.0'
        // も必要あり

        // language=xml
        String expect =
            "<script>" +
                "<java>${xmlunit.ignore}</java>" +
                "<major>${xmlunit.isNumber}</major>" +
                "<minor>${xmlunit.matchesRegex(^123.*)}</minor>" +
                "</script>";

//        ${xmlunit.matchesRegex(12.*)}

        // language=xml
        String actual =
            "<script>" +
                "<java>12</java>" +
                "<major>12</major>" +
                "<minor>123XXXXX</minor>" +
                "</script>    ";

        Diff diff = DiffBuilder.compare(expect).withTest(actual)
            .withDifferenceEvaluator(new PlaceholderDifferenceEvaluator())
            .build();

        assertThat(diff.getDifferences())
            .withFailMessage(diff.getDifferences().toString())
            .hasSize(0);

    }

}
