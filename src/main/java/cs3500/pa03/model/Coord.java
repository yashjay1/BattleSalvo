package cs3500.pa03.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an x-y point
 */
public class Coord {

  private int xcoord;
  private int ycoord;

  /**
   * Constructs a Coord object from JSON properties.
   *
   * @param xcoord The x-coordinate of this position represented as a JSON.
   * @param ycoord The y-coordinate of this position represented as a JSON.
   */
  @JsonCreator
  public Coord(@JsonProperty("x") int xcoord,
                      @JsonProperty("y") int ycoord) {
    this.xcoord = xcoord;
    this.ycoord = ycoord;

  }

  /**
   * Used for getting the X coordinate.
   *
   * @return the X coordinate
   */
  public int getX() {
    return xcoord;
  }

  /**
   * Used for setting the X coordinate.
   */
  public void setX(int xcoord) {
    this.xcoord = xcoord;
  }

  /**
   * Used for getting the Y coordinate.
   *
   * @return the Y coordinate
   */
  public int getY() {
    return ycoord;
  }

  /**
   * Used for setting the Y coordinate.
   */
  public void setY(int ycoord) {
    this.ycoord = ycoord;
  }

  /**
   * Used to check if two coordinates are equal.
   *
   * @return True if both Coordinates are the same point, else false.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Coord other = (Coord) obj;
    return xcoord == other.xcoord && ycoord == other.ycoord;
  }
}
