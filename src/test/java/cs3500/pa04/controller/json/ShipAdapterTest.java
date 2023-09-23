package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.Submarine;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ShipAdapterTest {

  Coord c1 = new Coord(2, 3);
  Coord c2 = new Coord(2, 4);
  Coord c3 = new Coord(2, 5);
  ArrayList loc1 = new ArrayList(Arrays.asList(c1, c2, c3));

  Coord c4 = new Coord(2, 3);
  Coord c5 = new Coord(3, 3);
  Coord c6 = new Coord(4, 3);
  ArrayList loc2 = new ArrayList(Arrays.asList(c4, c5, c6));

  Ship testSub1 = new Submarine(loc1);
  Ship testSub2 = new Submarine(loc2);


  @Test
  void determineDirVertical() {
    ShipAdapter adapter1 = new ShipAdapter(testSub1);
    assertEquals(c1, adapter1.getCoord());
    assertEquals("VERTICAL", adapter1.getDirection());
    assertEquals(3, adapter1.getLength());
  }

  @Test
  void determineDirHorizontal() {
    ShipAdapter adapter2 = new ShipAdapter(testSub2);
    assertEquals(c4, adapter2.getCoord());
    assertEquals("HORIZONTAL", adapter2.getDirection());
    assertEquals(3, adapter2.getLength());

  }
}