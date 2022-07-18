package kirimaru.biz.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionList {

  List<Question> value;

  public Map<Integer, List<Question>> groupByGenre() {
    return value.stream()
        .collect(Collectors.groupingBy(
            Question::getGenre
        ));
  }

  public Map<Locale, List<Question>> groupByLocale() {
    return value.stream()
        .collect(Collectors.groupingBy(
            Question::getLocale
        ));
  }


  private final Map<Locale, Translator> toJaActionMap =
      Map.of(
          Locale.ENGLISH, new FromEnToJa(),
          Locale.JAPANESE, new NoopTransrate(),
          Locale.FRENCH, new FromFrToJa()
      );

  public QuestionList translate() {
    final List<Question> list = new ArrayList<>();
    this.groupByLocale().forEach(
        (key, value) -> list.addAll(toJaActionMap.get(key).translate(value))
    );
    return new QuestionList(list);
  }

  public interface Translator {

    List<Question> translate(List<Question> question);
  }

  public class FromEnToJa implements Translator {

    @Override
    public List<Question> translate(List<Question> question) {
      return question.stream()
          .map(e -> e.toBuilder().locale(Locale.JAPANESE).build()
          ).collect(Collectors.toList());
    }
  }

  public class FromFrToJa implements Translator {

    @Override
    public List<Question> translate(List<Question> question) {
      return question.stream()
          .map(e -> e.toBuilder().locale(Locale.JAPANESE).build()
          ).collect(Collectors.toList());
    }
  }

  public class NoopTransrate implements Translator {

    @Override
    public List<Question> translate(List<Question> question) {
      return question;
    }
  }

}
