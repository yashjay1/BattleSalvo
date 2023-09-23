package cs3500.pa04.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.GameResult;

/**
 * Represents the final state of a game for serialization/deserialization with JSON.
 * This record is used to capture the game's result and the reason for its conclusion.
 */
public record EndGameJson(

    @JsonProperty ("result") GameResult result,

    @JsonProperty("reason") String reason) {
}
