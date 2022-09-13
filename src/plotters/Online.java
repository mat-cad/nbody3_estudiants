package plotters;

import util.StdDraw;
import util.Vector;

public class Online implements Plotter {
  private boolean trace;
  private double radius;
  private int pauseTime;
  private final double penRadius = 0.025;
  private final int canvasSize = 700;
  private int step = 0;
  private Vector[] previousPositions;

  public Online(boolean trace, double radius, int pauseTime) {
    this.trace = trace;
    this.radius = radius;
    this.pauseTime = pauseTime;
    createCanvas();
    if (trace) {
      StdDraw.clear(StdDraw.GRAY);
    }
  }

  private void createCanvas() {
    StdDraw.setCanvasSize(canvasSize, canvasSize);
    StdDraw.enableDoubleBuffering();
    StdDraw.setPenRadius(penRadius);
    StdDraw.setXscale(-radius, +radius);
    StdDraw.setYscale(-radius, +radius);
  }

  private void drawBodies(Vector[] bodiesPosition) {
    for (int i = 0; i < bodiesPosition.length; i++) {
      StdDraw.point(bodiesPosition[i].cartesian(0), bodiesPosition[i].cartesian(1));
    }
  }

  public void draw(Vector[] bodiesPosition) {
    if (trace) {
      if (step>0) {
        StdDraw.setPenColor(StdDraw.WHITE);
        drawBodies(previousPositions);
        StdDraw.setPenColor(StdDraw.BLACK);
        drawBodies(bodiesPosition);
        StdDraw.show();
        StdDraw.pause(pauseTime);
      }
      previousPositions = bodiesPosition;
    }
    else {
      StdDraw.clear();
      drawBodies(bodiesPosition);
      StdDraw.show();
      StdDraw.pause(pauseTime);
    }
    step += 1;
  }

  public void stop() {
    // do nothing, the window closes automatically
  }
}
