package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ShipType;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class CommandLineInterfaceTest {

  @Test
  void askFleetSize() {

    Map<ShipType, Integer> fleet = new HashMap<ShipType, Integer>();
    fleet.put(ShipType.BATTLESHIP, 3);
    fleet.put(ShipType.SUBMARINE, 1);
    fleet.put(ShipType.CARRIER, 2);
    fleet.put(ShipType.DESTROYER, 2);


    String input = "2 3 2 1\n2 3 2 1";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertEquals(fleet, sv.askFleetSize(10));

    assertThrows(NoSuchElementException.class,
        () -> sv.askFleetSize(0));
  }

  @Test
  void invalidFleetSize() {
    String input = "2 3 2 0";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertThrows(NoSuchElementException.class,
        () -> sv.askFleetSize(0));
  }

  @Test
  void showWelcome() {
    String input = "10 10\n10 10";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertEquals("10 10", sv.showWelcome());

  }

  @Test
  void illegalShowWelcome1() {
    String input = "10 10 10\n10 10 10";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertThrows(NoSuchElementException.class,
        () -> sv.showWelcome());

  }

  @Test
  void illegalShowWelcome2() {
    String input = "1 1\n1 1";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertThrows(NoSuchElementException.class,
        () -> sv.showWelcome());

  }

  @Test
  void illegalShowWelcome3() {
    String input = "100 100\n100 100";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertThrows(NoSuchElementException.class,
        () -> sv.showWelcome());

  }

  @Test
  void illegalShowWelcome4() {
    String input = "";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);

    assertThrows(NoSuchElementException.class,
        () -> sv.showWelcome());

  }


  @Test
  void askShot() {
    String input = "2 1\n2 1";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);
    List<Coord> listOfCoord = sv.askShot(3, 3, 1);
    assertEquals(1, listOfCoord.size());
    assertEquals(2, listOfCoord.get(0).getX());
    assertEquals(1, listOfCoord.get(0).getY());
  }

  @Test
  void illegalAskShot1() {
    String input = "20 10\n20 10";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);
    assertNull(sv.askShot(3, 3, 1));
  }

  @Test
  void illegalAskShot2() {
    String input = "2 10\n2 10";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);
    assertNull(sv.askShot(3, 3, 1));
  }

  @Test
  void illegalAskShot3() {
    String input = "-2 2\n-2 2";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);
    assertNull(sv.askShot(3, 3, 1));
  }

  @Test
  void illegalAskShot4() {
    String input = "2 -2\n2 -2";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);
    assertNull(sv.askShot(3, 3, 1));
  }

  @Test
  void illegalAskShot5() {
    String input = "2 2\n2 2";
    Reader reader = new InputReceiver(new StringReader(input));
    CommandLineInterface sv = new CommandLineInterface(reader);
    List<Coord> listOfCoord = sv.askShot(3, 3, 0);
    assertEquals(0, listOfCoord.size());
  }

  @Test
  void print() {
  }
}