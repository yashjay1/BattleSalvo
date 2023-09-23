package cs3500.pa04.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.ShipType;
import java.util.Map;

/**
 * Represents a JSON object containing the setup information received by the player,
 * including the board height, width, and fleet specifications.
 * This record is used for serialization/deserialization of the setup information in JSON format.
 */
public record ReceiveSetupJson(

    @JsonProperty("height") int height,

    @JsonProperty("width") int width,

    @JsonProperty("fleet-spec") Map<ShipType, Integer> fleetSpecs) {

}
