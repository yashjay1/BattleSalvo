package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Destroyer ship.
 */
public class Destroyer implements Ship {

  private final int size = 4;
  private List<Coord> location;
  private ShipStatus status;
  private int statusCounter;

  /**
   * Constructs a Destroyer ship with the specified location.
   *
   * @param location the list of coordinates representing the location of the ship.
   */
  Destroyer(List<Coord> location) {
    this.location = location;
    this.status = ShipStatus.ALIVE;
    this.statusCounter = 0;
  }

  /**
   * Updates the status of the ship based on the number of hits it has received.
   *
   * @return the updated status of the ship.
   */
  public ShipStatus updateStatus() {
    statusCounter++;
    if (statusCounter > 0 && statusCounter < size) {
      status = ShipStatus.HIT;
    } else if (statusCounter >= size) {
      status = ShipStatus.SUNK;
    }
    return this.status;
  }

  /**
   * Gets the size of the ship.
   *
   * @return the size of the ship.
   */
  public int getSize() {
    return size;
  }

  /**
   * Converts the ShipAdapter name to a String.
   *
   * @return a String with the type of boat.
   */
  @Override
  public String toString() {
    return "Destroyer";
  }

  /**
   * Sets the coordinates of the ship.
   *
   * @param coords the list of coordinates representing the location of the ship.
   */
  @Override
  public void setCoords(ArrayList<Coord> coords) {
    this.location = coords;
  }

  /**
   * Gets the coordinates of the ship.
   *
   * @return the list of coordinates representing the location of the ship.
   */
  public List<Coord> getLocation() {
    return location;
  }

  /**
   * Gets the status of the ship.
   *
   * @return the status of the ship.
   */
  public ShipStatus getStatus() {
    return status;
  }
}
