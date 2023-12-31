package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Test
  void makeEmptyBoard() {
    GameBoard gb = new GameBoard(3, 2);

    for (char[] row : gb.getBoard()) {
      for (char elem : row) {
        assertEquals(' ', elem);
      }
    }

    gb.makeEmptyBoard();

    for (char[] row : gb.getBoard()) {
      for (char elem : row) {
        assertEquals('0', elem);
      }
    }
  }

  @Test
  void getBoard() {
    GameBoard gb = new GameBoard(1, 1);
    char[][] testGb = new char[1][1];
    Arrays.fill(testGb[0], '0');
    gb.makeEmptyBoard();
    char expect = testGb[0][0];
    char actual = gb.getBoard()[0][0];
    assertEquals(expect, actual);
  }

  @Test
  void placeShips() {
    GameBoard gb = new GameBoard(6, 6);
    gb.makeEmptyBoard();
    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.BATTLESHIP, 1);
    fleet.put(ShipType.SUBMARINE, 2);
    fleet.put(ShipType.CARRIER, 1);
    fleet.put(ShipType.DESTROYER, 2);

    List<Ship> randomlyPlacedShip = gb.placeShips(fleet);

    assertTrue(randomlyPlacedShip.get(0) instanceof Carrier);
    assertTrue(randomlyPlacedShip.get(1) instanceof Battleship);
    assertTrue(randomlyPlacedShip.get(2) instanceof Destroyer);
    assertTrue(randomlyPlacedShip.get(4) instanceof Submarine);

    GameBoard gb2 = new GameBoard(6, 6);
    gb2.makeEmptyBoard();

    assertTrue(randomlyPlacedShip.get(0) instanceof Carrier);
    assertTrue(randomlyPlacedShip.get(1) instanceof Battleship);
    assertTrue(randomlyPlacedShip.get(2) instanceof Destroyer);
    assertTrue(randomlyPlacedShip.get(4) instanceof Submarine);

    List<Ship> randomlyPlacedShip2 = gb2.placeShips(fleet);
    assertNotEquals(randomlyPlacedShip, randomlyPlacedShip2);

  }

  @Test
  void numOfTurns() {
    GameBoard gb = new GameBoard(6, 6);
    gb.makeEmptyBoard();
    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.BATTLESHIP, 1);
    fleet.put(ShipType.SUBMARINE, 2);
    fleet.put(ShipType.CARRIER, 1);
    fleet.put(ShipType.DESTROYER, 2);

    List<Ship> randomlyPlacedShip = gb.placeShips(fleet);
    assertEquals(6, gb.numOfTurns());
    randomlyPlacedShip.get(5).updateStatus();
    randomlyPlacedShip.get(5).updateStatus();
    assertEquals(6, gb.numOfTurns());
    randomlyPlacedShip.get(5).updateStatus();
    randomlyPlacedShip.get(5).updateStatus();
    assertEquals(5, gb.numOfTurns());

  }

  @Test
  void hit() {

    char[][] board = new char[2][2];
    board[0][0] = '0';
    board[0][1] = 'S';
    board[1][0] = '0';
    board[1][1] = 'S';

    char[][] hiddenBoard = new char[2][2];
    hiddenBoard[0][0] = '0';
    hiddenBoard[0][1] = '0';
    hiddenBoard[1][0] = '0';
    hiddenBoard[1][1] = '0';

    Coord c1 = new Coord(0, 0);
    Coord c2 = new Coord(0, 1);
    Coord c3 = new Coord(1, 1);
    Ship sub = new Submarine(new ArrayList<>(Arrays.asList(c2, c3)));

    GameBoard gb = new GameBoard(2, 2, board, new ArrayList<>(Arrays.asList(sub)), hiddenBoard);

    List<Coord> loc = new ArrayList<>(Arrays.asList(c1, c2, c3));
    List<Coord> expectedLoc = new ArrayList<>(Arrays.asList(c2, c3));
    assertEquals(1, gb.hit(loc).get(0).getX());
    assertEquals(1, gb.hit(loc).get(0).getY());

  }

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStream));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  public void testDisplayBoardCpu() {

    char[][] board = {{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}};

    char[][] hiddenBoard = {{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}};

    Coord s1 = new Coord(4, 7);
    Coord s2 = new Coord(5, 7);
    Coord s3 = new Coord(6, 7);

    Ship sub = new Submarine(new ArrayList<>(Arrays.asList(s1, s2, s3)));
    List<Ship> ships = new ArrayList<>(Arrays.asList(sub));

    // Create a game board with a size of 10x10
    GameBoard gameBoard = new GameBoard(10, 10, board, ships, hiddenBoard);

    // Call the displayBoard method to display the opponent's board
    gameBoard.displayBoard(true);

    // Get the output from the ByteArrayOutputStream
    String output = outputStream.toString();

    // Compare the output against the expected output
    String expectedOutput = "\n" + "\n" + "--------------------------------------- \n" + "\n" + "\n"
        + "Opponent's Board\n" + "\n" + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n";
    assertEquals(expectedOutput, output);
  }

  @Test
  public void testDisplayBoardManual() {

    char[][] board = {{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}};

    char[][] hiddenBoard = {{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}};

    Coord s1 = new Coord(4, 7);
    Coord s2 = new Coord(5, 7);
    Coord s3 = new Coord(6, 7);

    Ship sub = new Submarine(new ArrayList<>(Arrays.asList(s1, s2, s3)));
    List<Ship> ships = new ArrayList<>(Arrays.asList(sub));

    // Create a game board with a size of 10x10
    GameBoard gameBoard = new GameBoard(10, 10, board, ships, hiddenBoard);

    // Call the displayBoard method to display the opponent's board
    gameBoard.displayBoard(false);

    // Get the output from the ByteArrayOutputStream
    String output = outputStream.toString();

    // Compare the output against the expected output
    String expectedOutput =
        "\n" + "\n" + "--------------------------------------- \n" + "\n" + "\n" + "Your Board\n"
            + "\n" + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 S 0 0 \n" + "0 0 0 0 0 0 0 S 0 0 \n"
            + "0 0 0 0 0 0 0 S 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 \n";
    assertEquals(expectedOutput, output);
  }
}