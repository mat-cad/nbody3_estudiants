package universes;

import integrators.Integrator;
import util.Vector;

// A universe object is a list of bodies in motion that are created in the constructor from the
// data in a certain plain text file. This file contains
// - the number of bodies, and for each,
// - the initial position in a plane (in x,y, no z)
// - the initial velocity vector (also 2d)
// - the mass
// all of them in SI units.
// A universe knows how to move the bodies, that is, tell them to update themselves according to
// the sum of gravitational forces each body is subject to by the other bodies.
public abstract class Universe {
    protected  int numBodies; // number of bodies
    protected  Body[] bodies; // array of n bodies
    protected double radius; // size of the universe when drawing

    public double getRadius() { return radius; }

    public Vector[] getAllBodiesPosition(){
        Vector[] positions = new Vector[numBodies];
        for (int i=0; i<numBodies; i++) {
            positions[i] = bodies[i].getPosition();
        }
        return positions;
    }
    
    public Vector computeForceOn(Body body, Body exclude) {
        Vector f = new Vector(new double[2]);
        for (Body b : bodies) {
            assert b != body;
            if ((b != body) && (b != exclude)) {
                f = f.plus(body.forceFrom(b));
            }
        }
        return f;
    }


    // With Euler and Leapfrog integrator:
    // update position and velocity of each body assuming forces and velocities are
    // constant during a certain small period of time dt. we are actually solving (integrating)
    // a differential equation to update speed and position, so if dt is too large maybe there will be
    // a noticeable error, but if it is too small the simulation will run very slowly.
    public void update(Integrator integrator) {
        long start = System.currentTimeMillis();
        // move the bodies "synchronoulsy" : freeze them, compute the new position and velocities
        // of the bodies and then update all the bodies at the same time.
        Body[] newBodies = new Body[numBodies];
        for (int i=0; i<numBodies; i++) {
            newBodies[i] = integrator.move(this, bodies[i]);
        }
        bodies = newBodies;

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        //System.out.println("Elapsed time " + timeElapsed + " milliseconds");
        // https://www.baeldung.com/java-measure-elapsed-time
    }

    public int getNumBodies() { return numBodies; }
}
