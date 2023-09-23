package cs3500.pa04.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import java.util.ArrayList;

/**
 * Represents a JSON object containing information about successful hits in the game.
 */
public record SuccessfulHitsJson(
    @JsonProperty("coordinates") ArrayList<Coord> coordinates) {

  /**
   * Retrieves the list of coordinates representing successful hits.
   *
   * @return the list of coordinates representing successful hits
   */
}
