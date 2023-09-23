package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.OldAutoPlayer;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.controller.json.EndGameJson;
import cs3500.pa04.controller.json.JoinJson;
import cs3500.pa04.controller.json.JsonUtils;
import cs3500.pa04.controller.json.MessageJson;
import cs3500.pa04.controller.json.ReceiveSetupJson;
import cs3500.pa04.controller.json.ReportDamageJson;
import cs3500.pa04.controller.json.SuccessfulHitsJson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test correct responses for different requests from the socket using a Mock Socket (mocket)
 */
public class ProxyControllerTest {

  private ByteArrayOutputStream testLog;
  private ProxyController controller;


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  /**
   * Check that the server returns a guess when given a hint.
   */
  @Test
  public void testJoinJson() {
    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.BATTLESHIP, 1);
    fleet.put(ShipType.SUBMARINE, 2);
    fleet.put(ShipType.CARRIER, 1);
    fleet.put(ShipType.DESTROYER, 2);

    // Create sample hint
    ReceiveSetupJson setupJson = new ReceiveSetupJson(6, 6, fleet);
    JoinJson joinJson = new JoinJson("yash", "SINGLE");
    EndGameJson endGameJson = new EndGameJson(GameResult.LOSE, "You lost");
    JsonNode jsonNode = createSampleMessage("join", joinJson);
    JsonNode jsonNode2 = createSampleMessage("end-game", endGameJson);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString(), jsonNode2.toString()));

    // Create a dealer
    try {
      this.controller = new ProxyController(socket, new OldAutoPlayer());
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.runGame();
  }


  /**
   * Check that the server correctly handles a setup JSON.
   */
  @Test
  public void testSetupJson() {
    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.BATTLESHIP, 1);
    fleet.put(ShipType.SUBMARINE, 2);
    fleet.put(ShipType.CARRIER, 1);
    fleet.put(ShipType.DESTROYER, 2);

    // Create sample hint
    ReceiveSetupJson setupJson = new ReceiveSetupJson(6, 6, fleet);
    JoinJson joinJson = new JoinJson("yash", "SINGLE");
    EndGameJson endGameJson = new EndGameJson(GameResult.LOSE, "You lost");
    JsonNode joinNode = createSampleMessage("join", joinJson);
    JsonNode setupNode = createSampleMessage("setup", setupJson);
    JsonNode endNode = createSampleMessage("end-game", endGameJson);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(joinNode.toString(), setupNode.toString(),
        endNode.toString()));

    // Create a dealer
    try {
      this.controller = new ProxyController(socket, new OldAutoPlayer());
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.runGame();
  }

  /**
   * Check that the server correctly handles a take-shot JSON.
   */
  @Test
  public void testTakeShotJson() {

    // Create sample hint
    JoinJson joinJson = new JoinJson("yash", "SINGLE");
    EndGameJson endGameJson = new EndGameJson(GameResult.LOSE, "You lost");
    JsonNode joinNode = createSampleMessage("join", joinJson);
    JsonNode takeShotNode = createSampleMessage("take-shots", null);
    JsonNode endNode = createSampleMessage("end-game", endGameJson);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(joinNode.toString(), takeShotNode.toString(),
        endNode.toString()));

    // Create a dealer
    try {
      this.controller = new ProxyController(socket, new OldAutoPlayer());
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.runGame();
  }

  /**
   * Check that the server correctly handles a report-damage JSON.
   */
  @Test
  public void testReportDamageJson() {

    // Create sample hint
    JoinJson joinJson = new JoinJson("yash", "SINGLE");
    ReportDamageJson reportJson = new ReportDamageJson(
        new Coord[] {new Coord(1, 2), new Coord(1, 3)});
    EndGameJson endGameJson = new EndGameJson(GameResult.LOSE, "You lost");
    JsonNode joinNode = createSampleMessage("join", joinJson);
    JsonNode reportDamageNode = createSampleMessage("report-damage", reportJson);
    JsonNode endNode = createSampleMessage("end-game", endGameJson);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(joinNode.toString(),
        reportDamageNode.toString(), endNode.toString()));

    // Create a dealer
    try {
      this.controller = new ProxyController(socket, new OldAutoPlayer());
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    assertThrows(NullPointerException.class,
        () -> controller.runGame());
  }

  /**
   * Check that the server correctly handles a successful-hits JSON.
   */
  @Test
  public void testSuccessfulHitsJson() {

    // Create sample hint
    SuccessfulHitsJson successfulHitsJson =
        new SuccessfulHitsJson(new ArrayList<>(Arrays.asList(new Coord(1, 2),
            new Coord(1, 3))));
    JoinJson joinJson = new JoinJson("yash", "SINGLE");
    EndGameJson endGameJson = new EndGameJson(GameResult.LOSE, "You lost");
    JsonNode joinNode = createSampleMessage("join", joinJson);
    JsonNode setupNode = createSampleMessage("successful-hits", successfulHitsJson);
    JsonNode endNode = createSampleMessage("end-game", endGameJson);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(joinNode.toString(), setupNode.toString(),
        endNode.toString()));

    // Create a dealer
    try {
      this.controller = new ProxyController(socket, new OldAutoPlayer());
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.runGame();
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Try converting the current test log to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);
      // No error thrown when parsing to a GuessJson, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail(e.getMessage());
    }
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName   name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson =
        new MessageJson(messageName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }
}