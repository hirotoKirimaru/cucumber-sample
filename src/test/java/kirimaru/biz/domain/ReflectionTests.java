package kirimaru.biz.domain;


import kirimaru.biz.domain.nest.Child;
import kirimaru.biz.domain.nest.GrandChild;
import kirimaru.biz.domain.nest.Parent;
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
    @Test
    void test_01() throws Exception {
      Parent target = Parent.builder()
          .child(Child.builder()
              .grandChild(GrandChild.builder()
                  .tax(123)
                  .build())
              .build())
          .build();

      SpelParserConfiguration config = new SpelParserConfiguration(true, true);
      StandardEvaluationContext context = new StandardEvaluationContext(target);

      ExpressionParser expressionParser = new SpelExpressionParser(config);
      Object parent = expressionParser.parseExpression("child.grandChild.tax").getValue(context);

      System.out.println(parent);
      assertThat(
          parent
      ).isEqualTo(123);

    }
  }


  @Nested
  class Reflect2 {
    @Test
    void test_01() throws Exception {
      GrandChild target = GrandChild.builder()
          .tax(123)
          .build();

      Class clazz = target.getClass();
      Method method = clazz.getDeclaredMethod("getTax");
      int result = (int) method.invoke(target);
      assertThat(
          result
      ).isEqualTo(123);

//      Class<? extends Parent> clazz = target.getClass();            // class は予約語なので慣例的に clazz や klass などにする
//      Method method = clazz.getDeclaredMethod("getChild", null); // 第二引数は呼び出すメソッドの引数の型の配列。無いときは null にする
//      method.setAccessible(true);
//      Child child = (Child)method.invoke(clazz, null);
//      Method childMethod = child.getClass().getDeclaredMethod("getGrandChild");
//      Object grandChild = childMethod.invoke(childMethod);
//      Method taxMethod = grandChild.getClass().getDeclaredMethod("getTax");
//      Object tax = taxMethod.invoke(taxMethod);

//      method.setAccessible(true);                            // 見えないはずのメソッドを見えるようにする魔法のメソッド
//      String result = (String) method.invoke(target, null);  // 第一引数にメソッドを呼び出すインスタンス、第二引数以後は実際の引数
//      System.out.println(result);
//
//      System.out.println(parent);
//      assertThat(
//          tax
//      ).isEqualTo(123);
    }


  }


}
