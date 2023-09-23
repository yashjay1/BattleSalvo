package cs3500.pa03.view;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ShipType;
import java.util.List;
import java.util.Map;

/**
 * The view module of BattleSalvo.
 */
public interface SalvoView {
  /**
   * Asks the user for the desired fleet size.
   *
   * @return A String containing the desired number of each ship type.
   */
  Map<ShipType, Integer> askFleetSize(int limit);

  /**
   * Used for displaying the welcome screen that asks for the board size.
   *
   * @return the dimensions of the board.
   */
  String showWelcome();

  /**
   * Asks the coordinates of a shot that needs to be fired.
   *
   * @return a coordinate to fire at.
   */
  List<Coord> askShot(int height, int width, int numOfShots);

  void print(String value);
}
