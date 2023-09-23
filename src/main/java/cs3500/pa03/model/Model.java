package cs3500.pa03.model;

import java.util.Map;

/**
 * Represents the model component in a game of Battleship.
 */
public interface Model {

  /**
   * Initializes the game with a specified board size and a mapping of ship types to
   * their respective quantities. This should be called at the start of every new game.
   *
   * @param height The height of the game board.
   * @param width  The width of the game board.
   * @param specs  A map where the keys are ShipTypes and the values are the number of
   *               each type of ship to be placed on the board.
   */
  void start(int height, int width, Map<ShipType, Integer> specs);

  /**
   * Checks if all ships have been sunk on the board.
   *
   * @return true if all ships have been sunk, false otherwise.
   */
  boolean allShipsSunk();
  
}
