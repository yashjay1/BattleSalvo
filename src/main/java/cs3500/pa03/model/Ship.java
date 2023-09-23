package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ShipAdapter on the board.
 */
public interface Ship {

  /**
   * Used for updating the status of the ship.
   */
  ShipStatus updateStatus();

  /**
   * Gets the size of the ship.
   *
   * @return the size of the ship.
   */
  int getSize();

  /**
   * Converts the ShipAdapter name to a String.
   *
   * @return a String with the type of boat.
   */
  String toString();

  /**
   * Sets the coordinates of the ship.
   *
   * @param coords the list of coordinates representing the ship's location
   */
  void setCoords(ArrayList<Coord> coords);

  /**
   * Gets the location of the ship.
   *
   * @return the list of coordinates representing the ship's location
   */
  List<Coord> getLocation();

  /**
   * Gets the status of the ship.
   *
   * @return the status of the ship
   */
  ShipStatus getStatus();
}
