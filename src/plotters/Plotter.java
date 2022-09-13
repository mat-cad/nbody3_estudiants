package plotters;

import util.Vector;

public interface Plotter {
  public void draw(Vector[] bodiesPosition);
  public void stop();
}


