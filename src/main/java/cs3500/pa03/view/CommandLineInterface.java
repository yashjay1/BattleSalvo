package cs3500.pa03.view;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ShipType;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a command-line interface for interacting with the game.
 * Prompts the user for inputs and displays information that would be useful to the user.
 */
public class CommandLineInterface implements SalvoView {
  private Reader userInput;
  private final Displayer display;


  /**
   * Constructs a CommandLineInterface object with the given user input reader.
   *
   * @param userInput the reader to use for user input
   */
  public CommandLineInterface(Reader userInput) {
    this.userInput = userInput;
    this.display = new InputDisplayer(new PrintStream(System.out));
  }

  /**
   * Constructs a CommandLineInterface object with the given displayer.
   *
   * @param display the displayer to use for displaying.
   */
  public CommandLineInterface(Displayer display) {
    if (display == null) {
      this.display = new InputDisplayer(new PrintStream(System.out));
    } else {
      this.display = display;
    }
  }

  /**
   * Asks the user for the desired fleet size.
   *
   * @param limit the maximum limit of fleet size
   * @return a map containing the desired number of each ship type
   * @throws IllegalArgumentException if the user input is invalid
   */
  public Map<ShipType, Integer> askFleetSize(int limit) throws IllegalArgumentException {
    print(
        "\n-----------------------------\nPlease enter your fleet in the order "
            + "[Carrier, Battleship, Destroyer, Submarine]\n-----------------------------\n");
    String userFleet = userInput.read();
    try {
      int fleetSum = 0;
      ArrayList<String> fleetList = new ArrayList<String>(Arrays.asList(userFleet.split(" ")));

      for (String s : fleetList) {
        fleetSum = fleetSum + Integer.parseInt(s);
      }

      for (String s : fleetList) {
        if (s.equals("0")) {
          print(
              "\n-----------------------------\nThere needs to be at least "
                  + "one of each type of fleet!\n-----------------------------\n");
          throw new IllegalArgumentException(
              "\nThere needs to be at least one of each type of fleet!\n");
        }
        if (fleetSum > limit) {
          print(
              "\n-----------------------------\nNumber of ships need to be lesser than or "
                  + "equal the lower dimension!\n-----------------------------\n");
          throw new IllegalArgumentException(
              "\n-----------------------------\nNumber of ships need to be lesser than or "
                  + "equal the lower dimension!\n-----------------------------\n");
        }
      }
    } catch (RuntimeException e) {
      askFleetSize(limit);
    }

    Map<ShipType, Integer> fleet = new HashMap<>();
    String[] fleetArr = userFleet.split(" ");
    fleet.put(ShipType.CARRIER, Integer.parseInt(fleetArr[0]));
    fleet.put(ShipType.BATTLESHIP, Integer.parseInt(fleetArr[1]));
    fleet.put(ShipType.DESTROYER, Integer.parseInt(fleetArr[2]));
    fleet.put(ShipType.SUBMARINE, Integer.parseInt(fleetArr[3]));
    return fleet;
  }

  /**
   * Used for displaying the welcome screen that asks for the board size.
   *
   * @return the dimensions of the board as a string
   * @throws IllegalArgumentException if the user input is invalid
   */
  public String showWelcome() throws IllegalArgumentException {
    print(
        "\n-----------------------------\nPlease enter a valid "
            + "height and width below:\n-----------------------------\n");
    String boardSize = userInput.read();

    try {
      ArrayList<String> boardDim = new ArrayList<String>(Arrays.asList(boardSize.split(" ")));
      for (String s : boardDim) {
        int num = Integer.parseInt(s);

        if (num < 6 || num > 15 || boardDim.size() > 2 || boardDim.size() <= 0) {
          print(
              "\n\n-----------------------------\nInvalid board size! The width and height "
                  + "must be in the range of 6 to 15.\n-----------------------------\n\n");
          throw new IllegalArgumentException(
              "\n\n-----------------------------\nInvalid board size! The width and height "
                  + "must be in the range of 6 to 15.\n-----------------------------\n\n");
        }
      }
    } catch (RuntimeException e) {
      showWelcome();
    }
    return boardSize;
  }

  /**
   * Asks the user for shot coordinates.
   *
   * @param height     the height of the game board
   * @param width      the width of the game board
   * @param numOfShots the number of shots to ask for
   * @return a list of shot coordinates
   */
  public List<Coord> askShot(int height, int width, int numOfShots) {
    print("\n-----------------------------\nPlease enter " + numOfShots
        + " coordinates to fire shots at!\n-----------------------------\n");
    List<Coord> listOfShots = new ArrayList<>();
    for (int i = 0; i < numOfShots; i++) {
      String shotCoord = userInput.read();
      String[] coord = shotCoord.split(" ");
      Coord temp = new Coord(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
      if (temp.getX() > height || temp.getY() > width || temp.getX() < 0 || temp.getY() < 0) {
        return null;
      } else {
        listOfShots.add(temp);
      }
    }
    return listOfShots;
  }

  @Override
  public void print(String value) {
    display.display(value);
  }
}
