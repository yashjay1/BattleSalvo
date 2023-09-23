package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalvoModelTest {

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
  void start() {
    SalvoModel model = new SalvoModel(new ManualPlayer(), new OldAutoPlayer());
    assertThrows(NoSuchElementException.class,
        () -> model.startSinglePlayer(6, 6, fleet));
  }

  @Test
  void allShipsSunk() {
    SalvoModel model = new SalvoModel(new ManualPlayer(), new OldAutoPlayer());
    assertThrows(NoSuchElementException.class,
        () -> model.startSinglePlayer(6, 6, fleet));
    assertTrue(model.allShipsSunk());
  }
}