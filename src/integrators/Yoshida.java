package integrators;

import universes.Body;
import universes.Universe;
import util.Vector;

// https://en.wikipedia.org/wiki/Leapfrog_integration
public class Yoshida extends Integrator {
  private final double cubicRoot2 = Math.cbrt(2);
  private final double w0 = cubicRoot2 / (2 - cubicRoot2);
  private final double w1 = 1. / (2. - cubicRoot2);
  private final double c1 = w1 / 2.;
  private final double c4 = c1;
  private final double c2 = (w0 + w1) / 2.;
  private final double c3 = c2;
  private final double d1 = w1;
  private final double d2 = w0;
  private final double d3 = d1;

  public Yoshida(double timeStep) {
    super(timeStep);
  }

  public Body move(Universe universe, Body body) {
    double dt = timeStep;
    double invMass = 1.0 / body.getMass();
    Vector v = body.getVelocity();
    Vector x = body.getPosition();
    Vector x1 = x.plus(v.scale(c1*dt));
    Body copy = body.clone();
    copy.setPosition(x1);
    Vector ax1 = universe.computeForceOn(copy, body).scale(invMass);
    Vector v1 = v.plus(ax1.scale(d1*dt));
    Vector x2 = x1.plus(v1.scale(c2*dt));
    copy.setPosition(x2);
    Vector ax2 = universe.computeForceOn(copy, body).scale(invMass);
    Vector v2 = v1.plus(ax2.scale(d2*dt));
    Vector x3 = x2.plus(v2.scale(c3*dt));
    copy.setPosition(x3);
    Vector ax3 = universe.computeForceOn(copy, body).scale(invMass);
    Vector v3 = v2.plus(ax3.scale(d3*dt));
    Vector xNew = x3.plus(v3.scale(c4*dt));
    Vector vNew = v3;
    copy.setPosition(xNew);
    copy.setVelocity(vNew);
    return copy;
  }

}
