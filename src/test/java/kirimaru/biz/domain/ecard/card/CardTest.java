package kirimaru.biz.domain.ecard.card;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {
  @Nested
  class Battle {

    @Test
    void test_01() {
      var emperor = new Emperor();
      var citizen = new Citizen();

      assertThat(emperor.battle(citizen)).isEqualTo(Card.BattleResult.WIN);
    }
  }

}
