package universes;

import util.Vector;

// A body with huge mass at the origin plus n other much smaller bodies around it, with
// random positions and velocities. Motion is circular (centered in the large body) or
// elliptic (one focus is the large body)
public class Nplus1 extends Universe {
  static final double MaxVelocity = 1e05;
  static final double MinVelocity = 1e04;
  static final double MinMass = 1e22;
  static final double MaxMass = 1e24;

  public Nplus1(int n) {
    radius = 1e12;
    numBodies = n+1;
    bodies = new Body[numBodies];
    bodies[0] = new Body(new Vector(new double[2]), new Vector(new double[2]), 1e39);
    for (int i = 1; i < numBodies; i++) {
      double angle = (2 * (Math.random() - 0.5))*Math.PI;
      double rho = (radius/4)*(Math.random() + 1);
      double rx = Math.cos(angle)*rho;
      double ry = Math.sin(angle)*rho;
      double vx = -ry/1000. + MinVelocity + 2 * (Math.random() - 0.5) * (MaxVelocity - MinVelocity);
      double vy = rx/1000. + MinVelocity + 2 * (Math.random() - 0.5) * (MaxVelocity - MinVelocity);
      double mass = MinMass + Math.random() * (MaxMass - MinMass);
      double[] position = {rx, ry};
      double[] velocity = {vx, vy};
      Vector r = new Vector(position);
      Vector v = new Vector(velocity);
      bodies[i] = new Body(r, v, mass);
      System.out.println(bodies[i]);
    }
  }
}
