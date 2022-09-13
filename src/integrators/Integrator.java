package integrators;

import universes.Body;
import universes.Universe;

public abstract class Integrator {
  protected double timeStep;
  Integrator(double timeStep) {
    this.timeStep = timeStep;
  }

  public abstract Body move(Universe universe, Body body);

  public double getTimeStep() {
    return timeStep;
  }
}

