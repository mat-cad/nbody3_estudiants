package plotters;

import util.Vector;

import java.io.*;


// Simply saves the bodies coordinates to an ascii file to be plotted later
// for example with Python Matplotlib :
//
public class Offline implements Plotter {
  private PrintWriter printWriter = null;
  // https://www.baeldung.com/java-write-to-file

  public Offline(String fname, int numBodies) {
    try {
      printWriter = new PrintWriter(new FileWriter(fname));
      printWriter.println(numBodies);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void draw(Vector[] bodiesPosition) {
    for (Vector position : bodiesPosition) {
      printWriter.printf("%f %f\n", position.cartesian(0), position.cartesian(1));
    }
  }

  public void stop() {
    printWriter.close();
  }
}
