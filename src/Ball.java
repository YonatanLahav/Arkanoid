import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Ball implements Sprite {
    //the center point of the ball.
    private Point center;
    //the radius of the ball.
    private int radius;
    //the color of the ball.
    private Color color;
    //the velocity of the ball.
    private Velocity velocity;
    //the game environment.
    private GameEnvironment gameEnvironment;

    /**
     * constructor for ball object.
     *
     * @param center          the Point center of the ball.
     * @param r               the radius of th ball.
     * @param color           the color of the ball.
     * @param gameEnvironment the game environment of the ball.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor for ball object.
     *
     * @param x               the x coordinate of the ball's center.
     * @param y               the y coordinate of the ball's center.
     * @param r               the radius of th ball.
     * @param color           the color of the ball.
     * @param gameEnvironment the game environment of the ball.
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Ball constructor.
     *
     * @param point center of the ball.
     * @param r     radius.
     */
    public Ball(Point point, int r) {
        this.center = point;
        this.radius = r;
        this.color = Color.gray;
        this.velocity = new Velocity();
        this.gameEnvironment = null;
    }

    /**
     * getX method return the x coordinate of the center.
     *
     * @return x coordinate of ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY method return the y coordinate of the center.
     *
     * @return y coordinate of ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize method return the radius of the ball.
     *
     * @return ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getColor method return the ball's color.
     *
     * @return ball's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * drawOn method draw on the surface object the ball by his center and color.
     *
     * @param surface the surface which draw the ball on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * setVelocity method set v to be the velocity of the ball.
     *
     * @param v the new Velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setVelocity method set Velocity(dx,dy) to be the velocity of the ball.
     *
     * @param dx dx parameter for the new velocity.
     * @param dy dy parameter for the new velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getVelocity return the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * moveOneStep method change the velocity of the ball when it hit the default screen border.
     * the default screen size is (200,200).
     */
    public void moveOneStep() {
        CollisionInfo collisionInfo;
        //the end of the trajectory segment.
        Point nextStep = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        //create the trajectory as Line.
        Line trajectory = new Line(this.center, nextStep);
        //get the closest collision between collision and the trajectory.
        collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        //check if there is any collisions.
        if (collisionInfo != null) {
            //set a new velocity to the ball.
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity));
        }
        //set a new center to the ball.
        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * Set a new center of the ball by point.
     *
     * @param newCenter the new center.
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * Set a new center of the ball by coordinates.
     *
     * @param xCoordinate x coordinate.
     * @param yCoordinate y coordinate.
     */
    public void setCenter(double xCoordinate, double yCoordinate) {
        this.center = new Point(xCoordinate, yCoordinate);
    }

    /**
     * get the center of the ball.
     *
     * @return center of the ball.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * the method add the ball to the gameLevel as a sprite.
     *
     * @param gameLevel the gameLevel object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.getBallCounter().increase(1);
    }

    /**
     * remove the ball from as a sprite from the gameLevel.
     *
     * @param gameLevel the current gameLevel.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
