package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa03.model.Coord;
import org.junit.jupiter.api.Test;

class ReportDamageJsonTest {

  Coord[] coords = {new Coord(3, 4), new Coord(1, 2), new Coord(4,  7)};
  ReportDamageJson rd = new ReportDamageJson(coords);

  @Test
  void testCoords() {
    assertArrayEquals(coords, rd.coords());
  }
}