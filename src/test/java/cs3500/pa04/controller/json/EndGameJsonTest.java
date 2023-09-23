package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.GameResult;
import org.junit.jupiter.api.Test;

class EndGameJsonTest {

  EndGameJson eg1 = new EndGameJson(GameResult.DRAW, "you drew");
  EndGameJson eg2 = new EndGameJson(GameResult.WIN, "you won!");
  EndGameJson eg3 = new EndGameJson(GameResult.LOSE, "you lost!");

  @Test
  void result() {
    assertEquals(GameResult.DRAW, eg1.result());
    assertEquals(GameResult.WIN, eg2.result());
    assertEquals(GameResult.LOSE, eg3.result());
  }

  @Test
  void reason() {
    assertEquals("you drew", eg1.reason());
    assertEquals("you won!", eg2.reason());
    assertEquals("you lost!", eg3.reason());

  }
}