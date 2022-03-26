package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * https://docs.oracle.com/en/java/javase/17/language/pattern-matching-instanceof-operator.html
 * https://docs.oracle.com/en/java/javase/17/language/pattern-matching-switch-expressions-and-statements.html
 */
class SwitchPatternMatchTests {
  public interface Shape {
    public static double getPerimeter(Shape shape) throws IllegalArgumentException {
      if (shape instanceof Rectangle) {
        Rectangle r = (Rectangle) shape;
        return 2 * r.length() + 2 * r.width();
      } else if (shape instanceof Circle) {
        Circle c = (Circle) shape;
        return 2 * c.radius() * Math.PI;
      } else {
        throw new IllegalArgumentException("Unrecognized shape");
      }
    }

    public static double getPerimeter2(Shape shape) throws IllegalArgumentException {
      if (shape instanceof Rectangle r) {
        return 2 * r.length() + 2 * r.width();
      } else if (shape instanceof Circle c) {
        return 2 * c.radius() * Math.PI;
      } else {
        throw new IllegalArgumentException("Unrecognized shape");
      }
    }

//    public static double getPerimeter2(Shape shape) throws IllegalArgumentException {
//      return switch (shape) {
//        case Rectangle r -> 2 * r.length() + 2 * r.width();
//        case Circle c    -> 2 * c.radius() * Math.PI;
//        default          -> throw new IllegalArgumentException("Unrecognized shape");
//      };
//    }
  }

  public class Rectangle implements Shape {
    final double length;
    final double width;
    public Rectangle(double length, double width) {
      this.length = length;
      this.width = width;
    }
    double length() { return length; }
    double width() { return width; }
  }

  public class Circle implements Shape {
    final double radius;
    public Circle(double radius) {
      this.radius = radius;
    }
    double radius() { return radius; }
  }



}
