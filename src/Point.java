/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Point {
    //double variable for x coordinate.
    private double x;
    //double variable for y coordinate.
    private double y;

    /**
     * Constructor for point objects.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance method calculate the distance between this point and other.
     *
     * @param other The point to which the distance is calculated.
     * @return double variable that represent the distance between the point
     */
    public double distance(Point other) {
        //The difference in the X-axis
        double dx = this.x - other.getX();
        //The difference in the Y-axis
        double dy = this.y - other.getY();
        //return the distance that calculated by the Pythagorean theorem.
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * equals method checks if the two points are equal.
     *
     * @param other Another point for which we will examine equality
     * @return True if the points are equal, otherwise False.
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -3);
        //check if the two point have the same coordinates.
        if (Math.abs(other.getX() - this.getX()) < epsilon && Math.abs(other.getY() - this.getY()) < epsilon) {
            return true;
        }
        return false;
    }

    /**
     * getX method.
     *
     * @return The x coordinate value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY method.
     *
     * @return The y coordinate value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * setX method change the x coordinate.
     *
     * @param newX the new x coordinate.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * setY method change the x coordinate.
     *
     * @param newY the new y coordinate.
     */
    public void setY(double newY) {
        this.y = newY;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}