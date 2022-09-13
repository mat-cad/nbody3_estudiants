package universes;

import util.Vector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Generic extends Universe {

  // read universe from standard input
  public Generic(String fname) {
    try {
      Scanner in = new Scanner(new FileReader(fname));
      numBodies = Integer.parseInt(in.next());
      // number of bodies
      System.out.println("Number of bodies = " + numBodies);

      // the set scale for drawing on screen
      radius = Double.parseDouble(in.next());
      System.out.println("Radius = " + radius);

      // read in the n bodies
      bodies = new Body[numBodies];
      for (int i = 0; i < numBodies; i++) {
        double rx = Double.parseDouble(in.next());
        double ry = Double.parseDouble(in.next());
        double vx = Double.parseDouble(in.next());
        double vy = Double.parseDouble(in.next());
        double mass = Double.parseDouble(in.next());
        double[] position = {rx, ry};
        double[] velocity = {vx, vy};
        Vector r = new Vector(position);
        Vector v = new Vector(velocity);
        bodies[i] = new Body(r, v, mass);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
