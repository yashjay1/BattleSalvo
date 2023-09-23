package cs3500.pa04.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a fleet of ships in a game for serialization/deserialization with JSON.
 */
public record Fleet(

    @JsonProperty("fleet") ArrayList<ShipAdapter> fleet) {

}
