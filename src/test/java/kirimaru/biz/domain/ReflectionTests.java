package kirimaru.biz.domain;


import kirimaru.biz.domain.nest.Child;
import kirimaru.biz.domain.nest.GrandChild;
import kirimaru.biz.domain.nest.Parent;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionTests {

  @Nested
  class Reflect {
    @DisplayName("publicなものを取得する")
    @Test
    void test_01() throws Exception {
      Parent target = Parent.builder()
          .child(Child.builder()
              .grandChild(GrandChild.builder()
                  .tax(123)
                  .description("説明")
                  .build())
              .build())
          .build();

      SpelParserConfiguration config = new SpelParserConfiguration(true, true);
      StandardEvaluationContext context = new StandardEvaluationContext(target);

      ExpressionParser expressionParser = new SpelExpressionParser(config);
      Object parent = expressionParser.parseExpression("child.grandChild.tax").getValue(context);
//      Object description = expressionParser.parseExpression("child.grandChild.description").getValue(context);

      System.out.println(parent);
      assertThat(
          parent
      ).isEqualTo(123);

//      assertThat(
//          description
//      ).isEqualTo("説明");


    }

    @Disabled("あとで再度調査する")
    @DisplayName("privateなものを取得する")
    @Test
    void test_02() throws Exception {
      Parent target = Parent.builder()
          .child(Child.builder()
              .grandChild(GrandChild.builder()
                  .description("説明")
                  .build())
              .build())
          .build();

      SpelParserConfiguration config = new SpelParserConfiguration(true, true);
      StandardEvaluationContext context = new StandardEvaluationContext(target);

      ExpressionParser expressionParser = new SpelExpressionParser(config);
      Object description = expressionParser.parseExpression("child.grandChild.description").getValue(context);

      assertThat(
          description
      ).isEqualTo("説明");
    }
  }


  @Nested
  class Reflect2 {
    @Test
    void test_01() throws Exception {
      GrandChild target = GrandChild.builder()
          .tax(123)
          .description("説明")
          .build();

      Class clazz = target.getClass();
      Method method = clazz.getDeclaredMethod("getTax");
      int result = (int) method.invoke(target);
      Method method2 = clazz.getDeclaredMethod("getDescription");
      method2.setAccessible(true);
      String result2 = (String) method2.invoke(target);
      assertThat(
          result
      ).isEqualTo(123);

      assertThat(
        result2
      ).isEqualTo("説明");

    }

    @Test
    void test_02() throws Exception {
      Parent target = Parent.builder()
          .child(Child.builder()
              .grandChild(GrandChild.builder()
                  .tax(123)
                  .build())
              .build())
          .build();

      Class clazz = target.getClass();
      Method method = clazz.getDeclaredMethod("getChild");
      Object child = method.invoke(target);
      Object grandChild = child.getClass().getDeclaredMethod("getGrandChild").invoke(child);
      Object tax = grandChild.getClass().getDeclaredMethod("getTax").invoke(grandChild);

      assertThat(
          tax
      ).isEqualTo(123);

    }


  }


}
