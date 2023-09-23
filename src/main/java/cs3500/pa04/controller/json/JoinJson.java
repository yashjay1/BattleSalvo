package cs3500.pa04.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a JSON object for joining a game, capturing the name and game type.
 * This record is used for serialization/deserialization of the join information in JSON format.
 */
public record JoinJson(

    @JsonProperty("name") String name1,

    @JsonProperty("game-type") String gameType) {


}
