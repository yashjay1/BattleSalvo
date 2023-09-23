package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.controller.Controller;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.Ship;
import cs3500.pa04.controller.json.Coordinates;
import cs3500.pa04.controller.json.EndGameJson;
import cs3500.pa04.controller.json.Fleet;
import cs3500.pa04.controller.json.JoinJson;
import cs3500.pa04.controller.json.JsonUtils;
import cs3500.pa04.controller.json.MessageJson;
import cs3500.pa04.controller.json.ReceiveSetupJson;
import cs3500.pa04.controller.json.ReportDamageJson;
import cs3500.pa04.controller.json.SendSuccessfulHits;
import cs3500.pa04.controller.json.ShipAdapter;
import cs3500.pa04.controller.json.SuccessfulHitsJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a proxy controller that communicates with a server using JSON messages.
 */
public class ProxyController implements Controller {

  private final Socket server;

  private final InputStream in;

  private final PrintStream out;

  private final Player player;


  private final ObjectMapper mapper = new ObjectMapper();

  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  /**
   * Construct an instance of a ProxyController.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player
   * @throws IOException if ....
   */
  public ProxyController(Socket server, Player player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
  }

  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON.
   */
  public void runGame() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Delegates the incoming message to the corresponding handler method based on the message name.
   *
   * @param message the received message as a MessageJson object
   */
  private void delegateMessage(MessageJson message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();

    if ("join".equals(name)) {
      handleJoin(arguments);
    } else if ("setup".equals(name)) {
      handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots(arguments);
    } else if ("report-damage".equals(name)) {
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      handleSuccessfulHits(arguments);
    } else if ("end-game".equals(name)) {
      handleEndGame(arguments);
    }
  }

  /**
   * Handles the "join" message by sending a "join" message with the player's name and game type.
   *
   * @param arguments the arguments of the "join" message
   */
  private void handleJoin(JsonNode arguments) {
    JoinJson joinArgs = new JoinJson(this.player.name(), "SINGLE");
    JsonNode reply = JsonUtils.serializeRecord(joinArgs);
    MessageJson messageJson = new MessageJson("join", reply);
    JsonNode replyNode = JsonUtils.serializeRecord(messageJson);
    this.out.println(replyNode);
  }

  /**
   * Handles the "setup" message by retrieving the player's fleet setup and sending it back.
   *
   * @param arguments the arguments of the "setup" message
   */
  private void handleSetup(JsonNode arguments) {
    ReceiveSetupJson setupArgs = this.mapper.convertValue(arguments, ReceiveSetupJson.class);

    List<Ship> fleet = getPlayerFleet(setupArgs);
    ArrayList<ShipAdapter> list = convertFleet(fleet);
    Fleet response = new Fleet(list);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    MessageJson messageJson = new MessageJson("setup", jsonResponse);
    JsonNode replyNode = JsonUtils.serializeRecord(messageJson);
    this.out.println(replyNode);
  }

  private ArrayList<ShipAdapter> convertFleet(List<Ship> fleet) {
    ArrayList<ShipAdapter> convertedFleet = new ArrayList<>();
    for (Ship ship : fleet) {
      convertedFleet.add(new ShipAdapter(ship));
    }

    return convertedFleet;
  }

  private List<Ship> getPlayerFleet(ReceiveSetupJson setupArgs) {
    List<Ship> fleet =
        this.player.setup(setupArgs.height(), setupArgs.width(), setupArgs.fleetSpecs());

    return fleet;
  }

  /**
   * Handles the "take-shots" message by retrieving the player's shots and sending them back.
   *
   * @param arguments the arguments of the "take-shots" message
   */
  private void handleTakeShots(JsonNode arguments) {

    Coordinates shots = getShots();


    JsonNode jsonResponse = JsonUtils.serializeRecord(shots);
    MessageJson messageJson = new MessageJson("take-shots", jsonResponse);
    JsonNode replyNode = JsonUtils.serializeRecord(messageJson);
    this.out.println(replyNode);
  }

  private Coordinates getShots() {
    List<Coord> shots = this.player.takeShots();

    ArrayList<Coord> convertedShots = new ArrayList<>(shots);
    return new Coordinates(convertedShots);
  }

  /**
   * Handles the "report-damage" message by retrieving the player's hits and sending them back.
   *
   * @param arguments the arguments of the "report-damage" message
   */
  private void handleReportDamage(JsonNode arguments) {
    ReportDamageJson reportDamageArgs = this.mapper.convertValue(arguments, ReportDamageJson.class);

    List<Coord> hits = getHits(reportDamageArgs);

    Coordinates hitJson = new Coordinates(new ArrayList<>(hits));

    JsonNode jsonResponse = JsonUtils.serializeRecord(hitJson);
    MessageJson messageJson = new MessageJson("report-damage", jsonResponse);
    JsonNode replyNode = JsonUtils.serializeRecord(messageJson);
    this.out.println(replyNode);
  }

  private List<Coord> getHits(ReportDamageJson reportDamageArgs) {

    Coord[] coords = reportDamageArgs.coords();
    List<Coord> coordsList = Arrays.asList(coords);

    return this.player.reportDamage(coordsList);
  }


  /**
   * Handles the "successful-hits" message by retrieving the player's successful hits.
   *
   * @param arguments the arguments of the "successful-hits" message
   */
  private void handleSuccessfulHits(JsonNode arguments) {
    SuccessfulHitsJson successfulHitsArgs =
        this.mapper.convertValue(arguments, SuccessfulHitsJson.class);

    getSuccessfulHits(successfulHitsArgs);

    JsonNode jsonResponse = JsonUtils.serializeRecord(new SendSuccessfulHits());
    MessageJson messageJson = new MessageJson("successful-hits", jsonResponse);
    JsonNode replyNode = JsonUtils.serializeRecord(messageJson);
    this.out.println(replyNode);
  }

  private void getSuccessfulHits(SuccessfulHitsJson successfulHitsArgs) {
    List<Coord> listOfHits = successfulHitsArgs.coordinates();
    this.player.successfulHits(listOfHits);
  }

  /**
   * Handles the "end-game" message by notifying the player about the game result and closing
   * the server connection.
   *
   * @param arguments the arguments of the "end-game" message
   */
  private void handleEndGame(JsonNode arguments) {
    EndGameJson endGameArgs = this.mapper.convertValue(arguments, EndGameJson.class);

    this.player.endGame(endGameArgs.result(), endGameArgs.reason());

    try {
      server.close();
    } catch (IOException e) {
      System.err.println("Socket is already closed!");
    }
  }
}
