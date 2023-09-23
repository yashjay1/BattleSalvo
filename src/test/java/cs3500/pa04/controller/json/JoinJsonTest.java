package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JoinJsonTest {
  JoinJson join = new JoinJson("yash", "SINGLE");

  @Test
  void name1() {
    assertEquals("yash", join.name1());
  }

  @Test
  void gameType() {
    assertEquals("SINGLE", join.gameType());
  }
}