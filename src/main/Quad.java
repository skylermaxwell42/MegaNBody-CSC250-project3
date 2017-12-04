public class Quad {

    private double xmid;
    private double ymid;
    private double len;

    //  Default Constructor
    public Quad(double xmid, double ymid, double len) {

        this.xmid = xmid;
        this.ymid = ymid;
        this.len = len;
    }

    //  Returns length
    public double len() {

        return len;
    }

    //  Returns True if Quadrant contains a body
    public boolean contains(double x, double y) {

        double midpnt = this.len / 2.0;

        boolean contains = (x >= this.xmid - midpnt &&
                            x <= this.xmid + midpnt &&
                            y >= this.ymid - midpnt &&
                            y <= this.ymid + midpnt);
        return contains;
    }

    // Creates Quadrants
    public Quad NE() {
        double x = this.xmid + this.len / 4.0;
        double y = this.ymid + this.len / 4.0;
        double len = this.len / 2.0;
        Quad NE = new Quad(x, y, len);
        return NE;
    }

    public Quad NW() {
        double x = this.xmid - this.len / 4.0;
        double y = this.ymid + this.len / 4.0;
        double length = this.len / 2.0;
        Quad NW = new Quad(x, y, length);
        return NW;
    }

    public Quad SE() {
        double x = this.xmid + this.len / 4.0;
        double y = this.ymid - this.len / 4.0;
        double len = this.len / 2.0;
        Quad SE = new Quad(x, y, len);
        return SE;
    }

    public Quad SW() {
        double x = this.xmid - this.len / 4.0;
        double y = this.ymid - this.len / 4.0;
        double len = this.len / 2.0;
        Quad SW = new Quad(x, y, len);
        return SW;
    }

    //  To String method
    public String toString() {
        String endl = "\n";
        for (int row = 0; row < this.len; row++) {
            for (int col = 0; col < this.len; col++) {
                if (row == 0 || col == 0 || row == this.len - 1 || col == this.len - 1)
                    endl += "*";
                else
                    endl += " ";
            }
            endl += "\n";
        }
        return endl;
    }

    //  Draws Quadrant
    public void draw() {
        StdDraw.rectangle(xmid, ymid, len / 2.0, len / 2.0);
    }
}
