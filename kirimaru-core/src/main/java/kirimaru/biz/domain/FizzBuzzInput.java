package kirimaru.biz.domain;

import java.util.Scanner;

public class FizzBuzzInput {
  public void main() {
    FizzBuzz fizzBuzz = new FizzBuzz();
    try (Scanner sc = new Scanner(System.in)) {
      System.out.println(fizzBuzz.convert(sc.nextInt()));
      System.out.println(fizzBuzz.convert(sc.nextInt()));
      System.out.println(fizzBuzz.convert(sc.nextInt()));
    }
  }
}
