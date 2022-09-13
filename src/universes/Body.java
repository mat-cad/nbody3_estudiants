package universes;

import util.Vector;

import java.math.BigDecimal;
import java.math.MathContext;

// A celestial body to simulate has a certain mass and a position and speed in space, all of them
// provided to the constructor. A body object knows how to compute the force another body exerts
// on it, and also how to update its position and velocity when it is subject to a certain force f
// assumed constant during a certain small time interval dt. Later this force will be computed as
// the sum of gravitational forces due to a number of other bodies.
public class Body implements Cloneable {
  private Vector position;
  private Vector velocity;
  private final double mass;
  private final double G;

  public Body(Vector pos, Vector vel, double mass, double... varargs) {
    // we use varargs because choreographies data assume G = 1.0
    this.position = pos;
    this.velocity = vel;
    this.mass = mass;
    assert varargs.length <= 1;
    if (varargs.length == 1) { // one argument passeds
      this.G = varargs[0];
    } else {
      this.G = 6.67430e-11;
    }
  }


  public Body clone() {
    // the integrator recreates all the bodies at some time so as to uptade all of them
    // simulatenously
    return new Body(position, velocity, mass, G);
  }

  public Vector getPosition() {
    return position;
  }

  public Vector getVelocity() {
    return velocity;
  }

  public double getMass() {
    return mass;
  }


  // computes the gravitational force due to another body
  public Vector forceFrom(Body b) {
    // F = G m_1 m_2/d_12^2 u_12, with u_12 unitary vector from mass 1 to mass 2, ie (p2-p1)/d_12
    Body a = this;
    Vector delta = b.position.minus(a.position);
    //double dist = delta.magnitude();
    //double magnitude = (G * a.mass * b.mass) / (dist * dist);
    double dist2 = delta.dot(delta);
    double magnitude = (G * a.mass * b.mass) / dist2;
    return delta.direction().scale(magnitude);
  }


  @Override
  public String toString() {
    return "position " + position.toString() + ", velocity " + velocity.toString()
            + ", mass " + mass + ", G " + G;
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

}
