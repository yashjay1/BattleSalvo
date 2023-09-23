package cs3500.pa04.controller.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SendSuccessfulHitsTest {


  @Test
  void testJson() {
    SendSuccessfulHits successfulHits = new SendSuccessfulHits();
    assertEquals(successfulHits, successfulHits);
  }

}