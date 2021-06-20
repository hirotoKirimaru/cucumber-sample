package kirimaru.biz.domain;

import java.text.MessageFormat;
import java.util.List;
import java.util.StringJoiner;


public class Concat {


  public String joiner(List<String> words) {

    var sj = new StringJoiner(",");
    for (String word : words) {
      sj.add(word);
    }
    return sj.toString();
  }

  public String joiner2(List<String> words) {
    return String.join(",", words);
  }

  public String builder(List<String> words) {
    var sb = new StringBuilder();
    for (String word : words) {
      sb.append(word);
      sb.append(",");
    }
    sb.delete(sb.length() - 1, sb.length());

    return sb.toString();
  }

  public String builder2(List<String> words) {
    var sb = new StringBuilder();
    for (int i = 0; i < words.size(); i++) {
      sb.append(words.get(i));
      if (words.size() - 1 != i) {
        sb.append(",");
      }
    }

    return sb.toString();
  }

  static class StringJoinerPractice {

    public String tab(List<String> words) {
      var sj = new StringJoiner("\t");
      for (String word : words) {
        sj.add(word);
      }
      return sj.toString();
    }

    public String addAiueo(List<String> words) {
      var sj = new StringJoiner("AIUEO");
      for (String word : words) {
        sj.add(word);
      }
      return sj.toString();
    }

    public String addBracket(List<String> words) {
      var sj = new StringJoiner(",", "{", "}");
      for (String word : words) {
        sj.add(word);
      }
      return sj.toString();
    }

    public String addBracketAndDoubleQuote(List<String> words) {
      var sj = new StringJoiner(",", "{", "}");
      for (String word : words) {
        sj.add(MessageFormat.format("\"{0}\"", word));
      }
      return sj.toString();
    }
  }

}
