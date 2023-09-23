package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class CoordinatesTest {

  Coord c1 = new Coord(2, 3);
  Coord c2 = new Coord(2, 4);
  Coord c3 = new Coord(2, 5);
  ArrayList loc1 = new ArrayList(Arrays.asList(c1, c2, c3));
  Coordinates coords = new Coordinates(loc1);

  @Test
  void coordinates() {
    assertArrayEquals(loc1.toArray(new Coord[loc1.size()]),
        coords.coordinates().toArray(new Coord[0]));
  }
}