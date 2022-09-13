package universes;

import util.Vector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// http://gminton.org/#choreointro
// https://analyticphysics.com/Gravitational%20Choreographies/Sim%C3%B3's%20Three-Body%20Choreographies%20in%20Action.htm
public class Choreography extends Universe {
  public Choreography(int nchoreography) {
    String fname = "data/simo-initial-conditions.txt";
    int numChroreographies = 345;
    assert (nchoreography>0) && (nchoreography<=numChroreographies);
    double c1=0, c2=0, c3=0, c4=0, c5=0;
    try {
      Scanner in = new Scanner(new FileReader(fname));
      for (int i=1; i<=nchoreography; i++) {
        c1 = Double.parseDouble(in.next());
        c2 = Double.parseDouble(in.next());
        c3 = Double.parseDouble(in.next());
        c4 = Double.parseDouble(in.next());
        c5 = Double.parseDouble(in.next());
      }
      System.out.println(c1+" "+c2+" "+c3+" "+c4+" "+c5);
      Vector r1 = new Vector(new double[]{-2*c1,0});
      Vector r2 = new Vector(new double[]{c1,c2});
      Vector r3 = new Vector(new double[]{c1,-c2});
      Vector v1 = new Vector(new double[]{0,-2*c4});
      Vector v2 = new Vector(new double[]{c3,c4});
      Vector v3 = new Vector(new double[]{-c3,c4});
      radius = 0.5;
      numBodies = 3;
      bodies = new Body[numBodies];
      double mass = 1./3 ;
      double G = 1.0;
      bodies[0] = new Body(r1, v1, mass, G);
      bodies[1] = new Body(r2, v2, mass, G);
      bodies[2] = new Body(r3, v3, mass, G);

      for (int i=0; i<3; i++) {
        System.out.println(bodies[i]);
      }
      System.out.println("mean position " + r1.plus(r2).plus(r3).scale(1./3.));
      System.out.println("mean velocity " + v1.plus(v2).plus(v3).scale(1./3.));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

}
