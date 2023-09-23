package cs3500.pa03.model;

import cs3500.pa03.view.CommandLineInterface;
import cs3500.pa03.view.InputDisplayer;
import cs3500.pa03.view.SalvoView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Represents the BattleSalvo Board.
 */
public class GameBoard {
  SalvoView view = new CommandLineInterface(new InputDisplayer(System.out));
  private final int height;
  private final int width;
  private final char[][] board;
  private final char[][] hiddenBoard;
  private List<Ship> shipOnBoard;
  private final List<Ship> sunkShip;
  private int turns;
  private List<Coord> shotsThatHit;

  /**
   * Constructs a GameBoard object with the specified height and width.
   *
   * @param height the height of the game board
   * @param width  the width of the game board
   */
  public GameBoard(int height, int width) {
    this.height = height;
    this.width = width;
    this.board = new char[height][width];
    this.hiddenBoard = new char[height][width];
    this.sunkShip = new ArrayList<>();
    this.shotsThatHit = new ArrayList<>();
  }

  /**
   * Constructor for testing.
   *
   * @param height the height of the game board.
   * @param width  the width of the game board.
   * @param board  the game board.
   */
  public GameBoard(int height, int width, char[][] board, List<Ship> shipOnBoard,
                   char[][] hiddenBoard) {
    this.height = height;
    this.width = width;
    this.board = board;
    this.hiddenBoard = hiddenBoard;
    this.sunkShip = new ArrayList<>();
    this.shipOnBoard = shipOnBoard;
    this.shotsThatHit = new ArrayList<>();
  }

  /**
   * Makes an empty board by filling all the elements
   * with an empty space. Empty space is represented using '0'.
   */
  public void makeEmptyBoard() {
    for (int i = 0; i < board.length; i++) {
      Arrays.fill(board[i], '0');
      Arrays.fill(hiddenBoard[i], '0');
    }
  }

  /**
   * Retrieves the current state of the game board.
   *
   * @return the 2D array of characters representing the game board
   */
  public char[][] getBoard() {
    return board;
  }

  /**
   * Places a ship of the specified type on the game board at a random CoordAdapter.
   *
   * @param ship the type of ship to place
   * @return the ship object representing the randomly placed ship
   */
  private Ship placeShip(ShipType ship) {
    Ship shipRep = null;
    Random random = new Random();
    int shipLength = ship.getSize();
    ArrayList<Coord> shipCoord = new ArrayList<>();
    Coord randomCoord = getRandomCoordinate(random);
    boolean isHorizontal = random.nextBoolean();

    while (!isValidSpace(randomCoord, shipLength, isHorizontal)) {
      randomCoord = getRandomCoordinate(random);
      isHorizontal = random.nextBoolean();
    }

    placeShipOnBoard(ship, randomCoord, shipLength, isHorizontal, shipCoord);

    switch (ship) {
      case SUBMARINE:
        shipRep = new Submarine(shipCoord);
        break;
      case DESTROYER:
        shipRep = new Destroyer(shipCoord);
        break;
      case BATTLESHIP:
        shipRep = new Battleship(shipCoord);
        break;
      case CARRIER:
        shipRep = new Carrier(shipCoord);
        break;
      default:
        shipRep = null;
    }

    return shipRep;
  }

  /**
   * Generates a random coordinate within the boundaries of the game board.
   *
   * @param random the Random object used for generating random numbers
   * @return a random coordinate within the game board boundaries
   */
  private Coord getRandomCoordinate(Random random) {
    return new Coord(random.nextInt(width), random.nextInt(height));
  }

  /**
   * Places the ship on the game board based on the given parameters.
   *
   * @param ship         the type of ship to place
   * @param randomCoord  the randomly generated starting coordinate for the ship
   * @param shipLength   the length of the ship
   * @param isHorizontal true if the ship is placed horizontally, false if vertically
   * @param shipCoord    the list of ship coordinates to be updated
   */
  private void placeShipOnBoard(ShipType ship, Coord randomCoord, int shipLength,
                                boolean isHorizontal, ArrayList<Coord> shipCoord) {
    if (isHorizontal) {
      for (int i = 0; i < shipLength; i++) {
        board[randomCoord.getY()][randomCoord.getX() + i] = ship.toString().charAt(0);
        shipCoord.add(new Coord(randomCoord.getX() + i, randomCoord.getY()));
      }
    } else {
      for (int i = 0; i < shipLength; i++) {
        board[randomCoord.getY() + i][randomCoord.getX()] = ship.toString().charAt(0);
        shipCoord.add(new Coord(randomCoord.getX(), randomCoord.getY() + i));
      }
    }
  }


  /**
   * Places ships from the specified fleet map on the game board.
   *
   * @param fleet the map representing the fleet of ships and their quantities
   * @return the list of ships placed on the game board
   */
  public ArrayList<Ship> placeShips(Map<ShipType, Integer> fleet) {
    ArrayList<Ship> shipsOnBoard = new ArrayList<>();
    ArrayList<ShipType> shipList = new ArrayList<>(
        Arrays.asList(ShipType.CARRIER, ShipType.BATTLESHIP, ShipType.DESTROYER,
            ShipType.SUBMARINE));
    int index = 0;

    while (!shipList.isEmpty()) {
      ShipType currentShip = shipList.get(index);
      int i = 0;

      while (i < fleet.get(currentShip)) {
        shipsOnBoard.add(placeShip(currentShip));
        i++;
      }

      shipList.remove(currentShip);
    }

    this.shipOnBoard = shipsOnBoard;
    this.turns = shipsOnBoard.size();
    return shipsOnBoard;
  }

  /**
   * Checks if the specified location is a valid space to place a ship.
   *
   * @param location     the location to check
   * @param shipLength   the length of the ship
   * @param isHorizontal true if the ship is placed horizontally, false if vertically
   * @return true if the location is a valid space to place the ship, false otherwise
   */
  private boolean isValidSpace(Coord location, int shipLength, boolean isHorizontal) {
    int row = location.getY();
    int col = location.getX();
    int numRows = board.length;
    int numCols = board[0].length;

    // Check if the location is within the board boundaries and not already occupied
    if (row < 0 || row >= numRows || col < 0 || col >= numCols || board[row][col] != '0') {
      return false;
    }

    // Check if there is enough space horizontally or vertically to place the ship
    for (int i = 0; i < shipLength; i++) {
      int currentRow = row;
      int currentCol = col;

      // Adjust the current row or column based on the orientation
      if (isHorizontal) {
        currentCol += i;
      } else {
        currentRow += i;
      }

      // Check if the adjusted location is within the board boundaries and not already occupied
      if (currentRow >= numRows || currentCol >= numCols || board[currentRow][currentCol] != '0') {
        return false;
      }
    }

    return true; // There is enough space horizontally or vertically
  }

  /**
   * Displays the current state of the game board.
   *
   * @param isCpu true if displaying the opponent's board (CPU),
   *             false if displaying the player's board
   */
  public void displayBoard(boolean isCpu) {
    view.print("\n\n--------------------------------------- \n");
    if (isCpu) {
      view.print("\n\nOpponent's Board\n\n");
      for (int i = 0; i < this.hiddenBoard.length; i++) {
        for (int j = 0; j < hiddenBoard[i].length; j++) {
          view.print(hiddenBoard[i][j] + " ");
        }
        view.print("\n");
      }
    } else {
      view.print("\n\nYour Board\n\n");
      for (int i = 0; i < this.board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          view.print(board[i][j] + " ");
        }
        view.print("\n");
      }
    }
  }

  /**
   * Processes the list of shots and updates the game board accordingly.
   *
   * @param shots the list of shots to be processed
   * @return the list of shots that hit a ship
   */
  public List<Coord> hit(List<Coord> shots) {
    List<Coord> shotHit = new ArrayList<>();

    for (Coord shot : shots) {
      if (board[shot.getY()][shot.getX()] != '0') {
        shotHit.add(shot);
        board[shot.getY()][shot.getX()] = 'H';
        hiddenBoard[shot.getY()][shot.getX()] = 'H';
        shotsThatHit.add(shot);
        checkAndUpdateShip(shot);
      } else {
        hiddenBoard[shot.getY()][shot.getX()] = 'M';
      }
    }
    return shotHit;
  }

  /**
   * Checks if a ship has been hit by the specified shot and updates its status accordingly.
   *
   * @param shot the shot to be checked against the ships
   */
  private void checkAndUpdateShip(Coord shot) {
    for (Ship ship : shipOnBoard) {
      for (Coord coord : ship.getLocation()) {
        if (coord.getX() == shot.getX() && coord.getY() == shot.getY()) {
          ship.updateStatus();
        }
      }
    }
  }

  /**
   * Retrieves the number of remaining turns (ships not sunk).
   *
   * @return the number of remaining turns
   */
  public int numOfTurns() {
    for (Ship ship : shipOnBoard) {
      if (ship.getStatus().equals(ShipStatus.SUNK) && !sunkShip.contains(ship)) {
        sunkShip.add(ship);
        turns--;
      }
    }
    return this.turns;
  }

  public List<Coord> getShotsThatHit() {
    return shotsThatHit;
  }

}
