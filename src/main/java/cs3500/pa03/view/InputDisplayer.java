package cs3500.pa03.view;

import java.io.IOException;
import java.util.Objects;

/**
 * Receives the input from the user.
 */
public class InputDisplayer implements Displayer {
  private final Appendable appendable;

  public InputDisplayer(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  /**
   * Displays a given message.
   *
   * @param value the content to display
   */
  @Override
  public void display(String value) {
    try {

      appendable.append(value);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
