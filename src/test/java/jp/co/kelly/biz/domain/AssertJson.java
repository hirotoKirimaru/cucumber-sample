package jp.co.kelly.biz.domain;

import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

class AssertJson {

    @DisplayName("特定の項目だけ無条件でJsonで無視する")
    @Test
    void test_01() throws JSONException {
        // language=json
        String expect = "{ \"time\" : {\n" +
                "  \"insertTime\": 201912010023,\n" +
                "  \"updateTime\": 202008111234\n" +
                "}," +
                "\"animal\": \"gorilla\"}\n";

        // language=json
        String actual = "{ \"time\" : {\n" +
                "  \"insertTime\": 201912010023,\n" +
                "  \"updateTime\": 999999999999\n" +
                "}," +
                "\"animal\": \"gorilla\"}\n";


        JSONAssert.assertEquals(expect, actual,
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("time.insertTime", (o1, o2) -> true),
                        new Customization("time.updateTime", (o1, o2) -> true)
                )
        );
    }

    @DisplayName("特定の項目だけ正規表現でJsonで無視する")
    @Test
    void test_02() throws JSONException {
        // language=json
        String expect = "{ \"time\" : {\n" +
                "  \"insertTime\": 201912010023,\n" +
                "  \"updateTime\": 202008111234\n" +
                "}," +
                "\"animal\": \"gorilla\"}\n";

        // language=json
        String actual = "{ \"time\" : {\n" +
                "  \"insertTime\": 201912010023,\n" +
                "  \"updateTime\": 202012300123\n" +
                "}," +
                "\"animal\": \"gorilla\"}\n";


        JSONAssert.assertEquals(expect, actual,
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("time.insertTime", new RegularExpressionValueMatcher<>(".*")),
                        new Customization("time.updateTime", new RegularExpressionValueMatcher<>(".*"))
                )
        );

    }


}
