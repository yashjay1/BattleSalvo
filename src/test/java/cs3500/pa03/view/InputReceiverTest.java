package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

class InputReceiverTest {

  @Test
  void testRead() {
    String input = "2 3 1 4";
    Readable readable = new StringReader(input);
    InputReceiver inputReceiver = new InputReceiver(readable);

    String result = inputReceiver.read();

    assertEquals(input, result);
  }
}