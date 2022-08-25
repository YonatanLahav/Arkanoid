import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Paddle implements Sprite, Collidable {

    //keyboard field.
    private biuoop.KeyboardSensor keyboard;
    //rectangle field.
    private Rectangle rectangle;
    //the gameLevel od the paddle.
    private GameLevel gameLevel;
    //the speed of the paddle.
    private int speed;
    //the width of the paddle.
    private int width;
    //the height of the paddle.
    private static final int HEIGHT = 20;


    /**
     * Paddle Constructor.
     *
     * @param gameLevel the game level.
     * @param width     width of the paddle.
     * @param speed     speed of the paddle.
     */
    public Paddle(GameLevel gameLevel, int width, int speed) {
        Point middlePoint = new Point(gameLevel.getScreenWidth() / 2 - 0.5 * width,
                gameLevel.getScreenHeight() - 2 * gameLevel.getFrameWidth());
        this.rectangle = new Rectangle(middlePoint, width, HEIGHT);
        this.gameLevel = gameLevel;
        this.keyboard = this.gameLevel.getRunner().getGui().getKeyboardSensor();
        this.speed = speed;
        this.width = width;
    }

    /**
     * moveLeft method move the paddle to the left side.
     */
    public void moveLeft() {
        double leftCorner = this.rectangle.getUpperLeft().getX() - this.speed;
        //check that the paddle isn't go outside the screen.
        if (leftCorner >= this.gameLevel.getFrameWidth()) {
            this.rectangle.getUpperLeft().setX(this.rectangle.getUpperLeft().getX() - this.speed);
        }
    }

    /**
     * moveRight method move the paddle to the right side.
     */
    public void moveRight() {
        double rightCorner = this.rectangle.getUpperLeft().getX() + rectangle.getWidth() + this.speed;
        //check that the paddle isn't go outside the screen.
        if (rightCorner <= this.gameLevel.getScreenWidth() - this.gameLevel.getFrameWidth()) {
            this.rectangle.getUpperLeft().setX(this.rectangle.getUpperLeft().getX() + this.speed);
        }
    }

    /**
     * drawOn method draw the paddle on d.
     *
     * @param d DrawSurface that need to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    /**
     * timePassed method move the paddle to the left/right according the key's that pressed by the user.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * getCollisionRectangle return the rectangle field.
     *
     * @return the rectangle field.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit method get a collisionPoint and currentVelocity and return a new velocity that calculated
     * according the the hit point on the paddle. the paddle have 5 regions.
     * region 1: change the velocity angle by -60 degrees.
     * region 2: change the velocity angle by -30 degrees.
     * region 3: change the velocity angle by 0 degrees.
     * region 4: change the velocity angle by 30 degrees.
     * region 5: change the velocity angle by 60 degrees.
     *
     * @param hitter          the hitter ball.
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the velocity of the object.
     * @return new velocity according to the hit point.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //temporary velocity.
        Velocity velocity = currentVelocity;
        //the upper left x coordinate of the rectangle.
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        //the dofference between the collision point and the upper x coordinate on x axes.
        double delta = Math.abs(collisionPoint.getX() - upperLeftX);
        //the difference between each one of the regions.
        int angle = 30;
        //calculate how many times 1/5 of the width of the paddle can divide delta.
        int q = (int) Math.floor(delta / (this.width / 5));
        //the ball hit the left edge of the paddle, change it fot fifth region.
        q = (q >= 5) ? 4 : q;
        //calculate the angle according to the region's collide.
        angle = (-2 + q) * angle;
        velocity = Velocity.fromAngleAndSpeed(angle,
                Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
        //return the relevant velocity.
        return velocity;
    }

    /**
     * intersectionPoints method return a list of intersection points between the line and the paddle.
     *
     * @param line line to check if intersection with.
     * @return list of intersection points.
     */
    @Override
    public List<Point> intersectionPoints(Line line) {
        return this.rectangle.intersectionPoints(line);
    }

    /**
     * add the paddle to the relevant gameLevel.
     *
     * @param g the Ga,e object that should add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}