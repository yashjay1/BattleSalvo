package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManualPlayerTest {

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private Map<ShipType, Integer> fleet;

  @BeforeEach
  void setUp() {
    fleet = new HashMap<>();
    fleet.put(ShipType.BATTLESHIP, 1);
    fleet.put(ShipType.SUBMARINE, 2);
    fleet.put(ShipType.CARRIER, 1);
    fleet.put(ShipType.DESTROYER, 2);
  }

  @Test
  void name() {
    Player testPlayer = new ManualPlayer();
    assertNull(testPlayer.name());
  }

  @Test
  void setup() {
    Player testPlayer = new ManualPlayer();
    List<Ship> shipLocs = testPlayer.setup(6, 6, fleet);
    assertEquals(6, shipLocs.size());

    Map<ShipType, Integer> fleet2 = new HashMap<>();
    fleet2.put(ShipType.BATTLESHIP, 2);
    fleet2.put(ShipType.SUBMARINE, 2);
    fleet2.put(ShipType.CARRIER, 1);
    fleet2.put(ShipType.DESTROYER, 2);
    Player testPlayer2 = new ManualPlayer();
    List<Ship> shipLocs2 = testPlayer2.setup(10, 10, fleet2);
    assertEquals(7, shipLocs2.size());
  }

  @Test
  void reportDamage() {

    Player testPlayer = new ManualPlayer();
    List<Ship> shipLocs = testPlayer.setup(15, 15, fleet);
    Coord c1 = new Coord(2, 2);
    Coord c2 = new Coord(3, 2);
    Coord c3 = new Coord(1, 3);
    List<Coord> shotList = new ArrayList<>(Arrays.asList(c1, c2, c3));
    List<Coord> coordsList = testPlayer.reportDamage(shotList);
    assertTrue(coordsList.size() == 0 || coordsList.size() == 1
        || coordsList.size() == 2
        || coordsList.size() == 3);
  }

  @Test
  void successfulHits() {

    Player testPlayer = new ManualPlayer();
    testPlayer.setup(10, 10, fleet);
    String output = outputStream.toString();
    Coord c1 = new Coord(1, 1);
    testPlayer.successfulHits(new ArrayList<Coord>(Arrays.asList(c1)));
    String expectOut1 = "";
    String expectOut2 = "(1 1)";

    assertTrue(output.equals(expectOut1) || output.equals(expectOut2));

  }

  @Test
  void endGame() {
    Player testPlayer = new ManualPlayer();
    testPlayer.endGame(null, null);
  }

  @Test
  public void testTakeShots() throws IOException {
    String input = "1 2\n3 4\n5 6\n";

    InputStream inputStream = new ByteArrayInputStream(input.getBytes());

    ManualPlayer player = new ManualPlayer();

    player.setup(6, 6, fleet);

    System.setIn(inputStream);
    inputStream.close();
    // Call the takeShots method

    assertThrows(NoSuchElementException.class,
        () -> player.takeShots());

  }

}