package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Carrier ship.
 */
public class Carrier implements Ship {

  private final int size = 6;
  private List<Coord> location;
  private List<Coord> hitLocation;
  private ShipStatus status;
  private int statusCounter;

  /**
   * Constructs a Carrier ship with the given location.
   *
   * @param location the list of coordinates representing the ship's location
   */
  public Carrier(List<Coord> location) {
    this.location = location;
    this.status = ShipStatus.ALIVE;
    this.statusCounter = 0;
  }


  /**
   * Used for updating the status of the ship.
   */
  @Override
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
   * Converts the ShipAdapter name to a String.
   *
   * @return a String with the type of ship
   */
  @Override
  public String toString() {
    return "Carrier";
  }

  /**
   * Sets the coordinates of the ship.
   *
   * @param coords the list of coordinates to set
   */
  @Override
  public void setCoords(ArrayList<Coord> coords) {
    this.location = coords;
  }

  /**
   * Gets the coordinates of the ship.
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
