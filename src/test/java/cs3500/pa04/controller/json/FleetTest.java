package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.Submarine;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class FleetTest {
  Coord c1 = new Coord(2, 3);
  Coord c2 = new Coord(2, 4);
  Coord c3 = new Coord(2, 5);
  ArrayList loc1 = new ArrayList(Arrays.asList(c1, c2, c3));

  Ship sub = new Submarine(loc1);
  ShipAdapter adaptedSub = new ShipAdapter(sub);

  @Test
  void fleet() {
    Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(adaptedSub)));
    assertEquals(adaptedSub, fleet.fleet().get(0));
  }
}