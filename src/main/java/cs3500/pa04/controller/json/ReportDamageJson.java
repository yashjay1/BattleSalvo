package cs3500.pa04.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;

/**
 * Represents a JSON object containing information about reported damage in the game.
 */
public record ReportDamageJson(
    @JsonProperty("coordinates") Coord[] coords) {

  /**
   * Retrieves the array of coordinates representing reported damage.
   *
   * @return the array of coordinates representing reported damage
   */
}
