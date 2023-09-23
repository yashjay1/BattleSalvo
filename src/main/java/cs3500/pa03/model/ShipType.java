package cs3500.pa03.model;

/**
 * Represents the types of ships in a naval battle game.
 */
public enum ShipType {

  /**
   * The Battleship ship type.
   */
  BATTLESHIP(5),

  /**
   * The Carrier ship type.
   */
  CARRIER(6),

  /**
   * The Destroyer ship type.
   */
  DESTROYER(4),

  /**
   * The Submarine ship type.
   */
  SUBMARINE(3);

  private final int size;

  /**
   * Constructs a ShipType with the specified size.
   *
   * @param size the size of the ship
   */
  ShipType(int size) {
    this.size = size;
  }

  /**
   * Returns the size of the ship.
   *
   * @return the size of the ship
   */
  public int getSize() {
    return size;
  }
}
