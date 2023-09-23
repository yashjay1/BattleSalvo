package cs3500.pa03.model;


import cs3500.pa03.view.CommandLineInterface;
import cs3500.pa03.view.InputDisplayer;
import cs3500.pa03.view.InputReceiver;
import cs3500.pa03.view.SalvoView;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Represents the player that is controlled by an actual person in the game.
 */
public class ManualPlayer implements Player {
  private String name;
  private GameBoard board;
  private int height;
  private int width;

  private int turns;

  private final SalvoView view = new CommandLineInterface(new InputDisplayer(System.out));

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return name;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.height = height;
    this.width = width;
    this.board = new GameBoard(height, width);
    board.makeEmptyBoard();

    List<Ship> shipLocations = board.placeShips(specifications);
    board.displayBoard(false);
    this.turns = board.numOfTurns();
    return shipLocations;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    SalvoView inputShots =
        new CommandLineInterface(new InputReceiver(new InputStreamReader(System.in)));
    List<Coord> coords = inputShots.askShot(height, width, turns);
    while (coords == null) {
      view.print("\n\nInvalid Coordinate entered! Re-enter your Salvo\n");
      coords = inputShots.askShot(height, width, turns);
    }
    return coords;
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hitShots = board.hit(opponentShotsOnBoard);
    board.displayBoard(false);
    this.turns = board.numOfTurns();
    return hitShots;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for (Coord shot : shotsThatHitOpponentShips) {
      view.print("\n\n(" + shot.getX() + " " + shot.getY() + ")");
    }
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {

  }
}
