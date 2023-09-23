package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestroyerTest {

  private Destroyer bs;
  private List<Coord> listOfCoords;

  @BeforeEach
  void setUp() {
    Coord c1 = new Coord(1, 3);
    Coord c2 = new Coord(2, 3);
    Coord c3 = new Coord(3, 3);
    Coord c4 = new Coord(4, 3);
    Coord c5 = new Coord(5, 3);
    listOfCoords = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5));
    bs = new Destroyer(listOfCoords);
  }

  @Test
  void updateStatus() {
    assertEquals(ShipStatus.ALIVE, bs.getStatus());
    bs.updateStatus();
    assertEquals(ShipStatus.HIT, bs.updateStatus());
    bs.updateStatus();
    bs.updateStatus();
    bs.updateStatus();
    bs.updateStatus();
    assertEquals(ShipStatus.SUNK, bs.updateStatus());
  }

  @Test
  void getSize() {
    assertEquals(4, bs.getSize());
  }

  @Test
  void testToString() {
    assertEquals("Destroyer", bs.toString());
  }

  @Test
  void setCoords() {
    Coord c1 = new Coord(1, 5);
    Coord c2 = new Coord(2, 5);
    Coord c3 = new Coord(3, 5);
    Coord c4 = new Coord(4, 5);
    Coord c5 = new Coord(5, 5);
    ArrayList<Coord> listOfCoordsNew = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5));

    assertEquals(listOfCoords, bs.getLocation());

    bs.setCoords(listOfCoordsNew);

    assertEquals(listOfCoordsNew, bs.getLocation());

  }

  @Test
  void getStatus() {
    assertEquals(ShipStatus.ALIVE, bs.getStatus());
  }

  @Test
  void getLocation() {
    assertEquals(listOfCoords, bs.getLocation());
  }
}