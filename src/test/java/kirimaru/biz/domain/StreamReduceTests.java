package kirimaru.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StreamReduceTests {
  @Getter
  @Builder
  @AllArgsConstructor
  public static class Human {
    private final String id;
    private final String div;
    private final List<Pet> petList;
    private final Wallet wallet;

    public boolean hasDog(){
      return petList.stream().anyMatch(e -> e.kind.equals("dog"));
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Pet {
      private final String id;
      private final String kind;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Wallet {
      private final String id;
      private final int money;
    }
  }

  @DisplayName("犬を買っている人のお金=1100と猫飼っている人のお金=10で合計1110になること")
  @Test
  void test_01() {
    List<Human> humanList = List.of(
        Human.builder()
        .id("A")
        .div("01")
        .wallet(Human.Wallet.builder()
            .money(1000)
            .build())
        .petList(List.of(
            Human.Pet.builder()
                .kind("dog")
                .build()
        ))
        .build(),
        Human.builder()
            .id("B")
            .div("01")
            .wallet(Human.Wallet.builder()
                .money(100)
                .build())
            .petList(List.of(
                Human.Pet.builder()
                    .kind("dog")
                    .build()
            ))
            .build(),
        Human.builder()
            .id("C")
            .div("01")
            .wallet(Human.Wallet.builder()
                .money(10)
                .build())
            .petList(List.of(
                Human.Pet.builder()
                    .kind("cat")
                    .build()
            ))
            .build()
    );

//    Map<String, List<Human>> collect =
//    Map<Stream<String>, List<Human>> collect = humanList.stream().collect(Collectors.groupingBy(e ->
//        e.petList.stream().map(f -> f.kind)
//    ));
//    List<Human> collect = humanList.stream().filter(Human::hasDog).collect(Collectors.toList());
//    List<Human> collect =
       Map<String, Object> collect = humanList.stream()
        .flatMap(e -> e.getPetList().stream())
        .map(Human.Pet::getKind)
        .distinct()
        .collect(Collectors.toMap(Function.identity(),
            k -> humanList.stream()
                .map(h -> h.getPetList().stream()
                    .map(f -> f.kind)
//                .map(Human.Pet::getKind)
//                    .anyMatch( i-> i.equals("dog"))
            )))
        ;
//        .collect(Collectors.toList());


//        assertThat(
//          collect.get("dog").stream().mapToInt(e -> e.)
//        )
    System.out.println(collect);

//    assertThat()
//    1110
  }

}
