package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.StringReader;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class SalvoViewTest {



  @Test
  void showWelcome() {
    String input = "7 10";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertEquals(input, sv.showWelcome());
  }

  @Test
  void invalidShowWelcomeArgs() {
    String input = "2 3 2 0";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertThrows(NoSuchElementException.class, () -> sv.showWelcome());

    String input2 = "";
    Reader reader2 = new InputReceiver(new StringReader(input2));
    CommandLineInterface sv2 = new CommandLineInterface(reader2);

    assertThrows(NoSuchElementException.class, () -> sv2.showWelcome());
  }

  @Test
  void invalidShowWelcomeBoardDim() {
    String input = "30 8";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertThrows(NoSuchElementException.class, () -> sv.showWelcome());

    String input2 = "7 90";
    Reader reader2 = new InputReceiver(new StringReader(input2));
    CommandLineInterface sv2 = new CommandLineInterface(reader2);

    assertThrows(NoSuchElementException.class, () -> sv2.showWelcome());
  }

}