package integrators;

import universes.Body;
import universes.Universe;
import util.Vector;

// https://en.wikipedia.org/wiki/N-body_simulation
public class Euler extends Integrator {
  public Euler(double timeStep) {
    super(timeStep);
  }


  @Override
  public Body move(Universe universe, Body body) {
    Vector force = universe.computeForceOn(body, null);
    Vector acceleration = force.scale(1 / body.getMass()); // a = f / m
    Vector velocity = body.getVelocity().plus(acceleration.scale(timeStep)); // v = a t
    Vector position = body.getPosition().plus(velocity.scale(timeStep)); // e = v t
    Body copy = body.clone();
    copy.setPosition(position);
    copy.setVelocity(velocity);
    return copy;
  }
}
