import integrators.*;
import plotters.*;
import simulator.*;
import universes.*;

public class Main {
  public static void main(String[] args) {
    testBasic();
    //testExperiments();
    //testFactoryMethod();
    //testAbstractFactory();
  }

  public static void testBasic() {
    int numBodies;
    double timeStep = 0;
    int numSteps = 0;
    Universe universe = null;
    Plotter plotter = null;
    Integrator integrator = null;

    int testCase = 6;

    if (testCase == 0) {
      String fname = "data/3body.txt";
      universe = new Generic(fname);
      timeStep = 1000.; // seconds
      integrator = new Euler(timeStep);
      boolean do_trace = true;
      int pauseTime = 0; // milliseconds
      plotter = new Online(do_trace, universe.getRadius(), pauseTime);
      numSteps = Integer.MAX_VALUE; // forever
    } else if (testCase == 1) {
      numBodies = 100;
      universe = new Random(numBodies);
      timeStep = 1000.; // seconds
      integrator = new Euler(timeStep);
      boolean do_trace = true;
      int pauseTime = 0; // milliseconds
      plotter = new Online(do_trace, universe.getRadius(), pauseTime);
      numSteps = (int) 1e4; // forever
    } else if (testCase == 2) {
      numBodies = 10;
      universe = new Nplus1(numBodies);
      // Online plot
      timeStep = 1e0; // seconds
      numSteps = Integer.MAX_VALUE;
      integrator = new LeapFrog(timeStep);
      boolean do_trace = true;
      int pauseTime = 0;
      plotter = new Online(do_trace, universe.getRadius(), pauseTime);
      // Offline
      // timeStep = 0.1;
      // numSteps = (int) 5e4;
      //plotter = new OfflinePlotter("nplus1.txt", universe.getNumBodies());
    } else if (testCase == 3) {
      numBodies = 10;
      universe = new Nplus1(numBodies);
      // Online plot
      timeStep = 1e-2; // seconds
      numSteps = (int) 1e5;
      integrator = new Yoshida(timeStep);
      plotter = new Offline("nplus1.txt", universe.getNumBodies());
    } else if (testCase == 4) {
      numBodies = 10;
      double gamma = 5e-5; // |z^._j| = \gamma |z_j| \forall j
      double angleVelPos = Math.PI/4.; // angle z^._j z_j \forall j
      universe = new CentralConfiguration(numBodies, gamma, angleVelPos);
      timeStep = 1e-1;
      numSteps = (int) 1e6;
      //integrator = new Euler(timeStep);
      integrator = new RK4(timeStep);
      plotter = new Offline("central_configuration.txt", universe.getNumBodies());
    } else if (testCase == 5) {
      numBodies = 10;
      double gamma = 5e-5; // |z^._j| = \gamma |z_j| \forall j
      double angleVelPos = Math.PI/4.; // angle z^._j z_j \forall j
      universe = new CentralConfiguration(numBodies, gamma, angleVelPos);
      timeStep = 1e1;
      numSteps = (int) 1e4;
      integrator = new Euler(timeStep);
      //integrator = new RK4(timeStep);
      boolean do_trace = true;
      int pauseTime = 0;
      plotter = new Online(do_trace, universe.getRadius(), pauseTime);
    } else if (testCase == 6) {
      final double[] Duration = new double[]{0.56, 0.9, 2.1, 2.2, 2.62, 2.9, 2.92, 2.96, 2.97, 3.0};
      // the longest body trajectory of the first 10 choreographies lasts 3 seconds
      int nchoreography = 2; // 0...9
      timeStep = 1e-4; // seconds
      numSteps = (int) (1.5*Duration[nchoreography] / timeStep);
      universe = new Choreography(nchoreography);
      integrator = new RK4(timeStep);
      boolean do_trace = true;
      int pauseTime = 0;
      plotter = new Online(do_trace, universe.getRadius(), pauseTime);
    } else if (testCase == 7) {
      final double[] Duration = new double[]{0.56, 0.9, 2.1, 2.2, 2.62, 2.9, 2.92, 2.96, 2.97, 3.0};
      // the longest body trajectory of the first 10 choreographies lasts 3 seconds
      int nchoreography = 2; // 0...9
      timeStep = 1e-6; // seconds
      numSteps = (int) (Duration[nchoreography] / timeStep);
      universe = new Choreography(nchoreography);
      integrator = new RK4(timeStep);
      plotter = new Offline("choreography.txt", universe.getNumBodies());
    } else {
      assert false : "inexistent test case " + testCase;
    }

    NBodySimulator simulator = new NBodySimulator(universe, plotter, integrator);
    simulator.simulate(0,numSteps*timeStep);
  }

  public static void testExperiments() {
    // TODO
  }

  public static void testFactoryMethod() {
    // TODO
  }

  public static void testAbstractFactory() {
    // TODO
  }

}
