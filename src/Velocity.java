import java.util.Random;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Velocity {
    //dx field for the velocity.
    private double dx;
    //dy field for the velocity.
    private double dy;

    /**
     * Velocity method is constructor for velocity object.
     *
     * @param dx dx argument for velocity.
     * @param dy dy argument for velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Velocity method is constructor for velocity object, generate random velocity if
     * there isn't given velocity to ball.
     */
    public Velocity() {
        Random rand = new Random();
        int speed = 10 + rand.nextInt(10);
        int angle = rand.nextInt(360);
        this.dx = Math.cos(Math.toRadians(angle)) * speed;
        this.dy = Math.sin(Math.toRadians(angle)) * speed;
    }

    /**
     * getDx method return the dx value of the velocity.
     *
     * @return double dx field.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy method return the dy value of the velocity.
     *
     * @return double dy value.
     */
    public double getDy() {
        return this.dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)

    /**
     * applyToPoint method change the point coordinate to another coordinate according to the velocity values.
     *
     * @param p the point that need to change.
     * @return Point with the new coordinate.
     */
    public Point applyToPoint(Point p) {
        //return new point according to velocity values.
        return new Point(p.getX() + 0.5 * dx, p.getY() + 0.5 * dy);
    }

    /**
     * applyToPoint method change the point coordinate to another coordinate according to the velocity values.
     * if the next step should be outside the screen size, than the new center of the ball will
     * near the limit of the screen.
     * the method calculate the ratio of the dx/dy that was apply and keep this ratio on the other axis.
     *
     * @param p         the center of the ball.
     * @param rectangle the rectangle that the ball in.
     * @param radius    the radius of the ball
     * @return the new ball's center.
     */
    public Point applyToPoint(Point p, Rectangle rectangle, int radius) {
        //ratio of movement of dx, dy.
        double ratioX = 1, ratioY = 1;
        //the simple case of movement, the next step is in the frame.
        Point tempPoint = new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
        //check if the next step is bigger then max x coordinate.
        if (p.getX() + radius + this.getDx() > rectangle.getUpperLeft().getX() + rectangle.getWidth()) {
            //calculate the x movement ratio that the ball can move.
            ratioX = Math.abs(p.getX() + radius + this.getDx()
                    - rectangle.getUpperLeft().getX() + rectangle.getWidth());
            ratioX = Math.abs(ratioX / this.getDx());
            //set the x coordinate of the center at the max valid coordinate.
            tempPoint.setX(rectangle.getUpperLeft().getX() + rectangle.getWidth() - radius);
        }
        //check if the next step is smaller then min x coordinate.
        if (p.getX() - radius + this.getDx() < rectangle.getUpperLeft().getX()) {
            //calculate the x movement ratio that the ball can move.
            ratioX = Math.abs(p.getX() - radius + this.getDx() - rectangle.getUpperLeft().getX());
            ratioX = Math.abs(ratioX / this.getDx());
            //set the x coordinate of the center at the min valid coordinate.
            tempPoint.setX(rectangle.getUpperLeft().getX() + radius);
        }
        //check if the next step is bigger then max y coordinate.
        if (p.getY() + radius + this.getDy() > rectangle.getUpperLeft().getY() + rectangle.getHeight()) {
            //calculate the y movement ratio that the ball can move.
            ratioY = Math.abs(p.getY() + radius + this.getDy()
                    - rectangle.getUpperLeft().getY() + rectangle.getHeight());
            ratioY = Math.abs(ratioY / this.getDy());
            //set the y coordinate of the center at the max valid coordinate.
            tempPoint.setY(rectangle.getUpperLeft().getY() + rectangle.getHeight() - radius);
        }
        //check if the next step is smaller then min y coordinate.
        if (p.getY() - radius + this.getDy() < rectangle.getUpperLeft().getY()) {
            //calculate the y movement ratio that the ball can move.
            ratioY = Math.abs(p.getY() - radius + this.getDy() - rectangle.getUpperLeft().getY());
            ratioY = Math.abs(ratioY / this.getDy());
            //set the y coordinate of the center at the min valid coordinate.
            tempPoint.setY(rectangle.getUpperLeft().getY());
        }
        //check if the ratio of movement in the x coordinate is bigger then ratio in y coordinate.
        if (ratioX > ratioY) {
            //if the x ratio is bigger, keep the same ratio on y axis movement.
            tempPoint.setX(p.getX() + ratioY * this.dx);
        } else {
            //if the y ratio is bigger, keep the same ratio on x axis.
            tempPoint.setY(p.getY() + ratioX * this.dy);
        }
        //return the new center of the ball.
        return tempPoint;
    }

    /**
     * fromAngleAndSpeed method create a velocity with by given speed and angle.
     * the new velocity calculated by separate the velocity vector to x values and y values.
     * then return the new velocity.
     *
     * @param angle the vector angle.
     * @param speed the vector size.
     * @return Velocity object with dx,dy by after separated to x and y values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //up is at 0 degrees, need to reduce the angle by 90.
        int reduceAngle = 90;
        return new Velocity(Math.cos(Math.toRadians(angle - reduceAngle)) * speed,
                Math.sin(Math.toRadians(angle - reduceAngle)) * speed);
    }

    /**
     * setDx method set a new dx field to the velocity.
     *
     * @param newDx the new dx.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * setDy method set a new dy field to the velocity.
     *
     * @param newDy the new dy.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }
}