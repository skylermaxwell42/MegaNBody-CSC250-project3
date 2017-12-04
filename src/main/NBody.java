import java.awt.Color;
import java.util.Scanner;

public class NBody {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        final double dt = 0.1;
        int numBodies = scnr.nextInt();
        double rad = scnr.nextDouble();

        StdDraw.show(0);
        StdDraw.setXscale(-rad, +rad);
        StdDraw.setYscale(-rad, +rad);

        //  Reads input data files
        Body[] bodies = new Body[numBodies];
        for (int i = 0; i < numBodies; i++) {
            double px   = scnr.nextDouble();
            double py   = scnr.nextDouble();
            double vx   = scnr.nextDouble();
            double vy   = scnr.nextDouble();
            double mass = scnr.nextDouble();
            int red     = scnr.nextInt();
            int green   = scnr.nextInt();
            int blue    = scnr.nextInt();
            Color color = new Color(red, green, blue);
            bodies[i]   = new Body(px, py, vx, vy, mass, color);
        }

        //  Runs simulation, updating quadtrees every dt seconds
        for (double time = 0.0; true; time = time + dt) {

            Quad q = new Quad(0, 0, rad * 2);
            BHTree t = new BHTree(q);

            //  Insert bodies in tree
            for (int i = 0; i < numBodies; i++) {
                if (bodies[i].in(q)) {
                   t.insert(bodies[i]);
               }
           }

            //  Updates Forces
            for (int i = 0; i < numBodies; i++) {
                bodies[i].resetForce();
                t.updateForce(bodies[i]);
                bodies[i].update(dt);
            }

            // Draw the bodies
            StdDraw.clear(StdDraw.BLACK);
            for (int i = 0; i < numBodies; i++) {
                bodies[i].draw();
            }
            
            StdDraw.show(10);
        }
    }
}
