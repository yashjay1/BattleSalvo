package cs3500.pa04.controller.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Direction;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;


/**
 * Represents an adapter class for serializing/deserializing the Ship object to/from JSON format.
 * This class is used to convert a Ship object into a format that can be serialized/deserialized
 * using Jackson ObjectMapper.
 */
public class ShipAdapter {

  /**
   * Coordinate of ship.
   */
  @JsonProperty("coord")
  private Coord coord;

  /**
   * Length of ship.
   */
  @JsonProperty("length")
  private int length;

  /**
   * Direction of ship.
   */
  @JsonProperty("direction")
  private String direction;

  /**
   * Constructs a ShipAdapter object based on the given Ship object.
   *
   * @param ship the Ship object to be adapted
   */
  public ShipAdapter(Ship ship) {
    this.coord = ship.getLocation().get(0);
    this.length = ship.getSize();
    this.direction = determineDirection(ship);
  }

  /**
   * Determines the direction of the ship (either "VERTICAL" or "HORIZONTAL") based on its location.
   *
   * @param ship the Ship object
   * @return the direction of the ship
   */
  private String determineDirection(Ship ship) {
    Coord coord1 = ship.getLocation().get(0);
    Coord coord2 = ship.getLocation().get(1);
    if (coord1.getX() == coord2.getX()) {
      return Direction.VERTICAL.toString();
    } else {
      return Direction.HORIZONTAL.toString();
    }
  }

  public Coord getCoord() {
    return coord;
  }

  public int getLength() {
    return length;
  }

  public String getDirection() {
    return direction;
  }
}
