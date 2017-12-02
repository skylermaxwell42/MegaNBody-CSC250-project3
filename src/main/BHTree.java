import java.awt.Color;

public class BHTree {
    private final double thresh = 0.5;

    private Body body;
    private Quad quad;
    private BHTree NW;
    private BHTree NE;
    private BHTree SW;
    private BHTree SE;

    //  Default Constructor
    public BHTree(Quad q) {
        this.quad = q;
        this.body = null;
        this.NW = null;
        this.NE = null;
        this.SW = null;
        this.SE = null;
    }

    //  Inset method for this Quad tree arch.
    public void insert(Body b) {

        //  Body = NULL
        if (body == null) {
            body = b;
            return;
        }

        //  Body is a leaf node
        if (! isExt()) {
            //  Insert body b
            putBody(b);

            // Update COM
            body = body.plus(b);

        }

        //  Body is an internal node
        else {
            // Create new child nodes
            NW = new BHTree(quad.NW());
            NE = new BHTree(quad.NE());
            SE = new BHTree(quad.SE());
            SW = new BHTree(quad.SW());

            // Insert body and b
            putBody(this.body);
            putBody(b);

            // Update the COM
            body = body.plus(b);

        }
    }


    // insert() helper method
    //Puts body b in a given quadrant
    private void putBody(Body b) {
        if (b.in(quad.NW())) {
            NW.insert(b);
        }
        else if (b.in(quad.NE())) {
            NE.insert(b);
        }
        else if (b.in(quad.SE())) {
            SE.insert(b);
        }
        else if (b.in(quad.SW())) {
            SW.insert(b);
        }
    }

    //  Returns True if external
    private boolean isExt() {
        return (NW == null && NE == null && SW == null && SE == null);
    }

    //  Updates force on body
    public void updateForce(Body b) {

        //  Base case
        if (body == null || b.equals(body))
            return;

        // If external
        if (isExt())
            b.addForce(body);

        //  Recursive Case
        else {
            // Calc ratio
            double s = quad.len();
            double d = body.distanceTo(b);

            // compare to threshold
            if ((s / d) < thresh)
                b.addForce(body);

            // recursively update Force
            else {
                NW.updateForce(b);
                NE.updateForce(b);
                SW.updateForce(b);
                SE.updateForce(b);
            }
        }
    }

    // Spaces Rep external nodes
    // Astericks rep internal nodes
    public String toString() {
        if (isExt())
            return " " + body + "\n";
        else
            return "*" + body + "\n" + NW + NE + SW + SE;
    }
}
