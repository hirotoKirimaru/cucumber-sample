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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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

//      SpelParserConfiguration config = new SpelParserConfiguration(true, true);
      StandardEvaluationContext context = new StandardEvaluationContext(target);

//      ExpressionParser expressionParser = new SpelExpressionParser(config);
      ExpressionParser expressionParser = new SpelExpressionParser();
      Object parent = expressionParser.parseExpression("child.grandChild.tax").getValue(context);
//      Object parent = expressionParser.parseExpression(".*tax").getValue(context);
//      Object description = expressionParser.parseExpression("child.grandChild.description").getValue(context);

      assertThat(parent).isEqualTo(123);
    }

    @Test
    void test_02() throws Exception {
      Parent target = Parent.builder()
          .children(List.of(
              Child.builder()
                  .grandChildren(List.of(
                      GrandChild.builder()
                          .tax(123)
                          .build())
                  )
                  .build())
          )
          .build();

//      SpelParserConfiguration config = new SpelParserConfiguration(true, true);
      StandardEvaluationContext context = new StandardEvaluationContext(target);

//      ExpressionParser expressionParser = new SpelExpressionParser(config);
      ExpressionParser expressionParser = new SpelExpressionParser();
      Object description = expressionParser.parseExpression("children[0].grandChildren[0].tax").getValue(context);

      assertThat(description).isEqualTo(123);
    }

    @Test
    void test_03() throws Exception {
      Parent target = Parent.builder()
          .children(List.of(
              Child.builder()
                  .grandChildren(List.of(
                      GrandChild.builder()
                          .build())
                  )
                  .build())
          )
          .build();

      StandardEvaluationContext context = new StandardEvaluationContext(target);

      ExpressionParser expressionParser = new SpelExpressionParser();
      Object dog = expressionParser.parseExpression("children[0].grandChildren[0].animals['dog']").getValue(context);
      Object cat = expressionParser.parseExpression("children[0].grandChildren[0].animals['cat']").getValue(context);
      Object mouse = expressionParser.parseExpression("children[0].grandChildren[0].animals['mouse']").getValue(context);

      assertThat(dog).isEqualTo(1);
      assertThat(cat).isEqualTo(3);
      assertThat(mouse).isEqualTo(10);
    }

    @Test
    void test_04() throws Exception {
      Parent target = Parent.builder()
          .children(List.of(
              Child.builder()
                  .grandChildren(List.of(
                      GrandChild.builder()
                          .build())
                  )
                  .build())
          )
          .build();

      StandardEvaluationContext context = new StandardEvaluationContext(target);

      ExpressionParser expressionParser = new SpelExpressionParser();
      Object size = expressionParser.parseExpression("children[0].grandChildren[0].animals.size() > 1").getValue(context);
      Object noSize = expressionParser.parseExpression("children[0].grandChildren[0].animals.size() == 0").getValue(context);

      assertThat(size).isEqualTo(true);
      assertThat(noSize).isEqualTo(false);
    }

    @Disabled("処理イメージ")
    @Test
    void test_05() throws Exception {
      Parent target = Parent.builder()
          .children(List.of(
              Child.builder()
                  .grandChildren(List.of(
                      GrandChild.builder()
                          .build())
                  )
                  .build())
          )
          .build();

      StandardEvaluationContext context = new StandardEvaluationContext(target);

      ExpressionParser expressionParser = new SpelExpressionParser();
      expressionParser.parseExpression("children[0].grandChildren[0].tax").setValue(context, 123);

      assertThat(target.getChildren().get(0).getGrandChildren().get(0).getTax()).isEqualTo(123);
    }

    @Disabled("処理イメージ")
    @Test
    void test_06() throws Exception {
      Parent target = Parent.builder()
          .children(List.of(
              Child.builder()
                  .grandChildren(List.of(
                      GrandChild.builder()
                          .build())
                  )
                  .build())
          )
          .build();

      StandardEvaluationContext context = new StandardEvaluationContext(target);

      ExpressionParser expressionParser = new SpelExpressionParser();
      expressionParser.parseExpression("children[0].grandChildren[0].animals").setValue(context, 123);

      assertThat(target.getChildren().get(0).getGrandChildren().get(0).getTax()).isEqualTo(123);
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

      Object child = target.getClass().getDeclaredMethod("getChild").invoke(target);
      Object grandChild = child.getClass().getDeclaredMethod("getGrandChild").invoke(child);
      Object tax = grandChild.getClass().getDeclaredMethod("getTax").invoke(grandChild);

      assertThat(tax).isEqualTo(123);
    }

    @DisplayName("直接変数を参照する")
    @Test
    void test_02_01() throws Exception {
      Parent target = Parent.builder()
          .child(Child.builder()
              .grandChild(GrandChild.builder()
                  .tax(123)
                  .build())
              .build())
          .build();

      Field childFiled = target.getClass().getDeclaredField("child");
      childFiled.setAccessible(true);
      Object child = childFiled.get(target);
      Field childField = child.getClass().getDeclaredField("grandChild");
      childField.setAccessible(true);
      Object grandChild = childField.get(child);
      Field fieldTax = grandChild.getClass().getDeclaredField("tax");
      fieldTax.setAccessible(true);
      Object tax = fieldTax.get(grandChild);

      assertThat(tax).isEqualTo(123);
    }

    @DisplayName("直接変数を参照する")
    @Test
    void test_02_02() throws Exception {
      GrandChild target = GrandChild.builder()
          .tax(123)
          .build();

      Field fieldTax = target.getClass().getDeclaredField("tax");
      fieldTax.setAccessible(true);
      Object tax = fieldTax.get(target);

      assertThat(tax).isEqualTo(123);
    }

    @DisplayName("直接配列を参照する")
    @Test
    void test_02_03() throws Exception {
      Child target = Child.builder()
          .grandChildren(List.of(
              GrandChild.builder()
                  .tax(123)
                  .build()
          )).build();

      Field fieldGrandChildren = target.getClass().getDeclaredField("grandChildren");
      fieldGrandChildren.setAccessible(true);
      List grandChildren = (List) fieldGrandChildren.get(target);
      Object grandChild = grandChildren.get(0);
      Field fieldTax = grandChild.getClass().getDeclaredField("tax");
      fieldTax.setAccessible(true);
      Object tax = fieldTax.get(grandChild);

      assertThat(tax).isEqualTo(123);
    }

    @DisplayName("直接変数を書き換える")
    @Test
    void test_02_04() throws Exception {
      GrandChild target = GrandChild.builder()
          .build();

      Field fieldTax = target.getClass().getDeclaredField("tax");
      fieldTax.setAccessible(true);
      fieldTax.set(target, 123);

      assertThat(target.getTax()).isEqualTo(123);
    }

    @Test
    void test_03() throws Exception {
      GrandChild target = GrandChild.builder()
          .build();

      Method method = target.getClass().getDeclaredMethod("computeMultiple", int.class, int.class);
      method.setAccessible(true);
      int result = (int) method.invoke(target, 100, 20);
      assertThat(result).isEqualTo(2000);
    }


    @Test
    void test_04() throws Exception {
      GrandChild target = GrandChild.builder()
          .build();

      Class clazz = target.getClass();
      Method method = clazz.getDeclaredMethod("computeMultipleArray", int[].class);
      method.setAccessible(true);
      int result = (int) method.invoke(target, new int[]{100, 20, 3, 4});
//      int result = (int) method.invoke(target, 100, 20, 3, 4);
      assertThat(result).isEqualTo(24000);
    }

    @Disabled("処理イメージ")
    @Test
    void test_05() throws Exception {
      GrandChild target = GrandChild.builder()
          .build();

      Class clazz = target.getClass();
      Method method = clazz.getDeclaredMethod("setTax", String.class);
      method.invoke(target, 123);
      assertThat(
          target.getTax()
      ).isEqualTo(123);
    }
  }


}
