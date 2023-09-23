package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordTest {

  private Coord c1;

  @BeforeEach
  void setUp() {
    this.c1 = new Coord(4, 3);
  }

  @Test
  void getX() {
    assertEquals(4, c1.getX());
  }

  @Test
  void setX() {
    assertEquals(4, c1.getX());
    c1.setX(20);
    assertEquals(20, c1.getX());
  }

  @Test
  void getY() {
    assertEquals(3, c1.getY());
  }

  @Test
  void setY() {
    assertEquals(3, c1.getY());
    c1.setY(10);
    assertEquals(10, c1.getY());
  }

  @Test
  void testEquals() {
    Coord c2 = new Coord(4, 3);
    Coord c3 = new Coord(9, 3);
    Coord c4 = new Coord(4, 2);
    assertTrue(c1.equals(c2));
    assertFalse(c1.equals(c3));
    assertFalse(c1.equals(c4));
    Coord c5 = new Coord(9, 0);
    assertFalse(c1.equals(c5));
  }
}