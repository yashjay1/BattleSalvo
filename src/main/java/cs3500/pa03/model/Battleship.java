package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Battleship ship.
 */
public class Battleship implements Ship {
  private final int size = 5;
  private List<Coord> location;
  private ShipStatus status;
  private int statusCounter;

  /**
   * Constructs a Battleship ship with the specified location.
   *
   * @param location the list of coordinates representing the ship's location
   */
  public Battleship(List<Coord> location) {
    this.location = location;
    this.status = ShipStatus.ALIVE;
    this.statusCounter = 0;
  }

  /**
   * Updates the status of the ship based on the number of hits it has received.
   *
   * @return the updated status of the ship
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
   * @return the size of the ship
   */
  public int getSize() {
    return size;
  }

  /**
   * Converts the ship name to a String representation.
   *
   * @return a String with the type of ship
   */
  @Override
  public String toString() {
    return "Battleship";
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
   * Gets the status of the ship.
   *
   * @return the status of the ship
   */
  public ShipStatus getStatus() {
    return status;
  }

  /**
   * Gets the location of the ship.
   *
   * @return the list of coordinates representing the ship's location
   */
  public List<Coord> getLocation() {
    return location;
  }
}
