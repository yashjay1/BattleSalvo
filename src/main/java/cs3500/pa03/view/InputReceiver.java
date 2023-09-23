package cs3500.pa03.view;

import java.util.Scanner;

/**
 * Receives the input from the user.
 */
public class InputReceiver implements Reader {

  private final Readable readable;

  public InputReceiver(Readable readable) {
    this.readable = readable;
  }

  /**
   * Reads the character sequence inputted.
   *
   * @return A String containing all the characters.
   */
  @Override
  public String read() {
    Scanner scanner = new Scanner(readable);
    String userInput = scanner.nextLine();
    return userInput;
  }
}
