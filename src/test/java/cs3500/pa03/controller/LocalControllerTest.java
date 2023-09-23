package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.OldAutoPlayer;
import cs3500.pa03.model.SalvoModel;
import cs3500.pa03.view.CommandLineInterface;
import cs3500.pa03.view.InputReceiver;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class LocalControllerTest {

  @Test
  void invalidRunGame() {
    //Testing
    LocalController controller =
        new LocalController(new SalvoModel(new ManualPlayer(), new OldAutoPlayer()),
            new CommandLineInterface(new InputReceiver(new InputStreamReader(System.in))));
    assertThrows(NoSuchElementException.class,
        () -> controller.runGame());
  }

  @Test
  void runGame() {
    LocalController controller =
        new LocalController(new SalvoModel(new ManualPlayer(), new OldAutoPlayer()),
            new CommandLineInterface(new InputReceiver(new StringReader("10 10\n1 1 2 1"))));
    assertThrows(NoSuchElementException.class,
        () -> controller.runGame());
  }
}