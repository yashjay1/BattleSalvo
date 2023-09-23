package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SuccessfulHitsJsonTest {

  Coord[] coords = {new Coord(4, 5),
      new Coord(2, 3)};

  SuccessfulHitsJson successfulHitsJson =
      new SuccessfulHitsJson(new ArrayList<>(Arrays.asList(coords)));

  @Test
  void coordinates() {
    assertArrayEquals(coords, successfulHitsJson.coordinates().toArray(new Coord[0]));
  }
}