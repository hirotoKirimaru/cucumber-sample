package kirimaru.biz.domain;

import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

class AssertJson {
    @Nested
    class 通常比較 {
        @DisplayName("普通の比較")
        @Test
        void test_00() throws JSONException {
            // language=json
            String expect = "{\n" +
                    "  \"animal\": \"gorilla\",\n" +
                    "  \"age\": 18\n" +
                    "}\n";

            // language=json
            String actual = "{\n" +
                    "  \"age\": 18,\n" +
                    "  " +
                    "\"animal\": \"gorilla\"\n" +
                    "}\n";

            JSONAssert.assertEquals(expect, actual, JSONCompareMode.STRICT_ORDER);
        }

        @Disabled
        @DisplayName("配列の比較")
        @EnumSource(value = JSONCompareMode.class)
        @ParameterizedTest
        void test_01(JSONCompareMode mode) throws JSONException {
            // language=json
            String expect = "{ \n" +
                    "  " +
                    "\"animal\": [\n" +
                    "    {\n" +
                    "      " +
                    "\"id\": 1,\n" +
                    "      \"name\": \"gorilla\",\n" +
                    "      \"age\": 18\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 2,\n" +
                    "      \"name\": \"gorilla\",\n" +
                    "      \"age\": 22\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}\n";

            // language=json
            String actual = "{ \n" +
                    "  " +
                    "\"animal\": [\n" +
                    "    {\n" +
                    "      \"age\": 22,\n" +
                    "      \"name\": \"gorilla\",\n" +
                    "      \"id\": 2\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 1,\n" +
                    "      \"name\": \"gorilla\",\n" +
                    "      \"age\": 18\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}\n";

            JSONAssert.assertEquals(expect, actual, mode);
        }
    }



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
                        new Customization("time.insertTime", new RegularExpressionValueMatcher<>(".*2019.*")),
                        new Customization("time.updateTime", new RegularExpressionValueMatcher<>(".*2020.*"))
                )
        );

    }


}
