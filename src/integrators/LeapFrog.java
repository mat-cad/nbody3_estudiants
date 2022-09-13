package integrators;

import universes.Body;
import universes.Universe;
import util.Vector;

// https://en.wikipedia.org/wiki/N-body_simulation
// https://en.wikipedia.org/wiki/Leapfrog_integration and reference [2] there
public class LeapFrog extends Integrator {

  public LeapFrog(double timeStep) {
    super(timeStep);
  }

  @Override
  public Body move(Universe universe, Body body) {
    Vector force = universe.computeForceOn(body, null);
    Vector a = force.scale(1 / body.getMass()); // a = f / m
    Vector v = body.getVelocity();
    Vector r = body.getPosition();

    Vector rNew = (r.plus(v.scale(timeStep)))
            .plus((a.scale(timeStep*timeStep)).scale(0.5));

    Body copy = body.clone();
    copy.setPosition(rNew);
    Vector forceNew = universe.computeForceOn(copy, body);
    Vector aNew = forceNew.scale(1 / body.getMass()); // a = f / m
    Vector vNew = v.plus(((a.plus(aNew)).scale(timeStep)).scale(0.5));
    copy.setVelocity(vNew);
    return copy;
  }
}
