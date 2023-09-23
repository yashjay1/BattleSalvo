package cs3500.pa04.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import java.util.ArrayList;

/**
 * Represents a record of a list of coordinates.
 * This class is used to group a list of Coords and can be serialized/deserialized using JSON.
 */
public record Coordinates(

    @JsonProperty("coordinates") ArrayList<Coord> coordinates) {

}
