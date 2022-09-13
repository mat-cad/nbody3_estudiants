package universes;

import util.Vector;

public class Random extends Universe {
  static final double MaxVelocity = 1e05;
  static final double MinVelocity = 1e04;
  static final double MinMass = 1e20;
  static final double MaxMass = 1e30;

  public Random(int nb) {
    radius = 1e12;
    numBodies = nb;
    bodies = new Body[numBodies];
    for (int i = 0; i < numBodies; i++) {
      double rx = 2 * (Math.random() - 0.5) * radius / 2;
      double ry = 2 * (Math.random() - 0.5) * radius / 2;
      double vx = MinVelocity + 2 * (Math.random() - 0.5) * (MaxVelocity - MinVelocity);
      double vy = MinVelocity + 2 * (Math.random() - 0.5) * (MaxVelocity - MinVelocity);
      double mass = MinMass + Math.random() * (MaxMass - MinMass);
      double[] position = {rx, ry};
      double[] velocity = {vx, vy};
      Vector r = new Vector(position);
      Vector v = new Vector(velocity);
      bodies[i] = new Body(r, v, mass);
    }
  }
}
