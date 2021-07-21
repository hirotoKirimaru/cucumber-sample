package kirimaru.biz.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
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
