package simulator;

import integrators.Integrator;
import plotters.Plotter;
import universes.Universe;

public class NBodySimulator {
    private Universe universe;
    private Plotter plotter;
    private Integrator integrator;

    public NBodySimulator(Universe universe, Plotter plotter, Integrator integrator) {
        this.universe = universe;
        this.plotter = plotter;
        this.integrator = integrator;
    }

    // draw the n bodies. By default draw from instant 0 of simulation, forever
    public void simulate() {
        simulate(0.0, Double.MAX_VALUE);
    }

    // this is to simulate (just calculate) and after some time start drawing,
    // until some other time afterward. In this way we don't need to
    // wait too much to see what happens after a certain time of simulating.
    public void simulate(double initTime, double endTime) {
        for (double t = 0; t < endTime; t += integrator.getTimeStep()) {
            universe.update(integrator);
            if (t>= initTime) {
                plotter.draw(universe.getAllBodiesPosition());
                /*
                util.Vector[] p = universe.getAllBodiesPosition();
                for (int i=0; i<p.length; i++) {
                    System.out.println(p[i]);
                }
                 */
            }
        }
        plotter.stop();
    }
}
