package cs3500.pa03.controller;

import cs3500.pa03.model.SalvoModel;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.view.SalvoView;
import java.util.Map;

/**
 * The Controller module of BattleSalvo.
 */
public class LocalController implements Controller {

  private final SalvoModel model;
  private final SalvoView view;

  /**
   * The constructor for the Controller module of BattleSalvo.
   */
  public LocalController(SalvoModel model, SalvoView view) {
    this.model = model;
    this.view = view;

  }

  /**
   * Used for running the BattleSalvo game.
   */
  public void runGame() {
    String boardDim = view.showWelcome();
    String[] dimensions = boardDim.split(" ");
    Map<ShipType, Integer> spec = view.askFleetSize(
        Math.min(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1])));

    this.model.startSinglePlayer(Integer.parseInt(dimensions[0]),
        Integer.parseInt(dimensions[1]), spec);

  }
}
