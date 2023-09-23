package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.ShipType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ReceiveSetupJsonTest {

  Map<ShipType, Integer> some = new HashMap<>();


  ReceiveSetupJson setupJson = new ReceiveSetupJson(6, 7, some);


  @Test
  void height() {
    assertEquals(6, setupJson.height());
  }

  @Test
  void width() {
    assertEquals(7, setupJson.width());
  }

  @Test
  void fleetSpecs() {
    assertEquals(some, setupJson.fleetSpecs());
  }
}