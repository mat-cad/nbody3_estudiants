package universes;

import util.Vector;

// n bodies angularly equispaced in a circle with equal mass, same velocity magnitude and constant
// angle between position vector and velocity of each body
// http://www.scholarpedia.org/article/Central_configurations
public class CentralConfiguration extends Universe {

  public CentralConfiguration(int n, double gamma, double angleVelPos) {
    radius = 1e11;
    numBodies = n;
    bodies = new Body[numBodies];
    double mass = 1e33;
    double distance = 0.4*radius;
    double velocityMagnitude = gamma*distance;
    for (int i=0; i<n; i++) {
      double anglePos = (2*Math.PI*i)/n;
      double rx = distance*Math.cos(anglePos);
      double ry = distance*Math.sin(anglePos);
      double vx = velocityMagnitude*Math.cos(anglePos + angleVelPos);
      double vy = velocityMagnitude*Math.sin(anglePos + angleVelPos);
      bodies[i] = new Body(new Vector(new double[]{rx,ry}), new Vector(new double[]{vx,vy}), mass);
    }
  }
}
