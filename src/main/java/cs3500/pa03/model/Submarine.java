package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Submarine ship.
 */
public class Submarine implements Ship {

  private final int size = 3;
  private List<Coord> location;
  private ShipStatus status;
  private int statusCounter;

  public Submarine(List<Coord> location) {
    this.location = location;
    this.status = ShipStatus.ALIVE;
    this.statusCounter = 0;
  }

  /**
   * Used for updating the status of the ship.
   */
  public ShipStatus updateStatus() {
    statusCounter++;
    if (statusCounter > 0 && statusCounter < size) {
      status = ShipStatus.HIT;
    } else if (size >= statusCounter) {
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
    return "Submarine";
  }

  /**
   * Sets the coordinates of the ship.
   *
   * @param coords the list of coordinates representing the ship's location
   */
  @Override
  public void setCoords(ArrayList<Coord> coords) {
    this.location = coords;
  }


  /**
   * Gets the location of the ship.
   *
   * @return the list of coordinates representing the ship's location
   */
  public List<Coord> getLocation() {
    return location;
  }

  /**
   * Gets the status of the ship.
   *
   * @return the status of the ship
   */
  public ShipStatus getStatus() {
    return status;
  }

}
