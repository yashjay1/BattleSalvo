package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa04.Driver;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class DriverTest {

  @Test
  public void constructorTest() {
    Driver driver = new Driver();

  }

  @Test
  public void singlePlayer() {
    String [] args = new String[0];
    assertThrows(NoSuchElementException.class,
        () -> Driver.main(args));
  }

  @Test
  public void multiPlayer() {
    String [] args = {"0.0.0.0", "32115"};
    assertThrows(IllegalArgumentException.class,
        () -> Driver.main(args));
  }

}