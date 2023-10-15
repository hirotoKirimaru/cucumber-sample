package kirimaru.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import kirimaru.biz.domain.Human;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ToClassHelperTests {

  ToClassHelper helper = new ToClassHelper();

  @Test
  public void test_01() {
    //language=JSON
    String json = "{\n" +
        "  \"age\": 18,\n" +
        "  \"name\": \"kirimaru\"\n" +
        "}";

    Human expect = Human.builder()
        .age(18)
        .name("kirimaru")
        .build();

    Human actual = helper.fromJson(json, Human.class);

    assertThat(actual).isEqualTo(expect);
  }

  @Test
  public void test_02() {
    var input = getClass().getResourceAsStream("/kirimaru/human.json");

    Human expect = Human.builder()
        .age(18)
        .name("kirimaru")
        .build();

    Human actual = helper.fromJson(input, Human.class);

    assertThat(actual).isEqualTo(expect);
  }

  @Test
  public void test_03() {
    Path path = Paths.get("src", "test", "resources", "kirimaru", "human.json");

    Human expect = Human.builder()
        .age(18)
        .name("kirimaru")
        .build();

    Human actual = helper.fromJson(path, Human.class);

    assertThat(actual).isEqualTo(expect);
  }

  @Test
  public void test_04() {
    var input = getClass().getResourceAsStream("/kirimaru/human2.json");

    Human expect = Human.builder()
        .age(18)
        .name("kirimaru")
        .children(List.of(
            Human.builder()
                .age(10)
                .name("gorilla")
                .build(),
            Human.builder()
                .age(30)
                .name("dog")
                .build(),
            Human.builder()
                .age(40)
                .name("cat")
                .build()
            ))
        .build();

    Human actual = helper.fromJson(input, Human.class);

    assertThat(actual).isEqualTo(expect);
  }


}
