package cs3500.pa03.model;


import cs3500.pa03.view.CommandLineInterface;
import cs3500.pa03.view.InputDisplayer;
import cs3500.pa03.view.SalvoView;
import java.util.List;
import java.util.Map;

/**
 * The model module of BattleSalvo.
 */
public class SalvoModel {
  private final Player player1;
  private final Player player2;

  private List<Ship> player1ShipLocations;
  private List<Ship> player2ShipLocations;

  private final SalvoView view = new CommandLineInterface(new InputDisplayer(System.out));

  /**
   * Constructs a new instance of the SalvoModel class.
   * Initializes player1 as a ManualPlayer and CPU as an AutoPlayer.
   */
  public SalvoModel(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  /**
   * Starts the model by initializing the players and sets
   * up the game with the specified parameters.
   *
   * @param height The height of the game grid.
   * @param width  The width of the game grid.
   * @param specs  The specifications for the ships to be placed on the grid.
   */
  public void startSinglePlayer(int height, int width, Map<ShipType, Integer> specs) {

    this.player1ShipLocations = this.player1.setup(height, width, specs);
    this.player2ShipLocations = this.player2.setup(height, width, specs);

    while (allShipsSunk()) {
      List<Coord> player1Shots = this.player1.takeShots();
      List<Coord> cpuShots = this.player2.takeShots();
      List<Coord> cpuHits = this.player2.reportDamage(player1Shots);
      view.print(
          "\n-----------------------------\nYour hits that were "
              + "successful\n-----------------------------\n");
      this.player1.successfulHits(cpuHits);
      List<Coord> player1Hits = this.player1.reportDamage(cpuShots);
      view.print(
          "\n-----------------------------\nOpponent's hits that were "
              + "successful\n-----------------------------\n");
      this.player1.successfulHits(player1Hits);
    }
  }


  /**
   * Checks if all ships of both players have been sunk.
   *
   * @return {@code true} if all ships have been sunk for both players, {@code false} otherwise.
   */
  public boolean allShipsSunk() {
    boolean bothPlayersAlive = true;
    int numOfP1ShipsSunk = 0;
    int numOfCpuShipsSunk = 0;
    for (Ship s : player1ShipLocations) {
      if (s.getStatus().equals(ShipStatus.SUNK)) {
        numOfP1ShipsSunk++;
      }
    }

    for (Ship s : player2ShipLocations) {
      if (s.getStatus().equals(ShipStatus.SUNK)) {
        numOfCpuShipsSunk++;
      }
    }

    if (numOfP1ShipsSunk == player1ShipLocations.size()
        || numOfCpuShipsSunk == player2ShipLocations.size()) {
      bothPlayersAlive = false;
    }

    return bothPlayersAlive;
  }
}
