package kirimaru.biz.domain;

public class FizzBuzz {

  public void execute() {
    for (int i = 1; i <= 100; i++) {
      System.out.println(convert(i));
    }
  }

  public String convert(int input) {
    var sb = new StringBuilder();
    if (input % 3 == 0) sb.append("Fizz");
    if (input % 5 == 0) sb.append("Buzz");
    if (sb.length() == 0) sb.append(input);
    return sb.toString();
  }

}
