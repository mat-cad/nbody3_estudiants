package integrators;

import universes.Body;
import universes.Universe;
import util.Vector;

public class RK4 extends Integrator {

    public RK4(double timeStep) {
      super(timeStep);
    }

    @Override
    public Body move(Universe universe, Body body) {
      double h = timeStep;
      Vector force;
      double invMass = 1 / body.getMass();
      Body copy = body.clone();
      Vector r = body.getPosition();
      Vector v = body.getVelocity();

      force = universe.computeForceOn(body, null);
      Vector k1v = force.scale(invMass); // a = f / m;
      Vector k1r = v;
      copy.setPosition(r.plus(k1r.scale(h).scale(0.5)));
      force = universe.computeForceOn(copy, body);
      Vector k2v = force.scale(invMass);
      Vector k2r = v.plus(k1v.scale(h).scale(0.5));
      copy.setPosition(r.plus(k2r.scale(h).scale(0.5)));
      force = universe.computeForceOn(copy, body);
      Vector k3v = force.scale(invMass);
      Vector k3r = v.plus(k2v.scale(h).scale(0.5));
      copy.setPosition(r.plus(k3r.scale(h)));
      force = universe.computeForceOn(copy, body);
      Vector k4v = force.scale(invMass);
      Vector k4r = v.plus(k3v.scale(h));

      Vector vNew = v.plus(k1v.plus(k2v.scale(2)).plus(k3v.scale(2)).plus(k4v).scale(h/6.));
      Vector rNew = r.plus(k1r.plus(k2r.scale(2)).plus(k3r.scale(2)).plus(k4r).scale(h/6.));
      copy.setPosition(rNew);
      copy.setVelocity(vNew);
      return copy;
    }
}
