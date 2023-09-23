package cs3500.pa04;

import cs3500.pa03.controller.LocalController;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.OldAutoPlayer;
import cs3500.pa03.model.SalvoModel;
import cs3500.pa03.view.CommandLineInterface;
import cs3500.pa03.view.InputReceiver;
import cs3500.pa04.controller.ProxyController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      LocalController controller =
          new LocalController(new SalvoModel(new ManualPlayer(), new OldAutoPlayer()),
              new CommandLineInterface(new InputReceiver(new InputStreamReader(System.in))));
      controller.runGame();
    } else if (args.length == 2) {

      try {
        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        ProxyController controller1 = new ProxyController(socket, new OldAutoPlayer());
        controller1.runGame();

      } catch (IOException e) {
        throw new IllegalArgumentException("Invalid server IP or port");
      }
    }
  }
}