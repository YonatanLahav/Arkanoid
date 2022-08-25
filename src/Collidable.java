/**
 * @author Yonatan Lahav.
 * ID 316099548.
 * Collidable interface represents objects in which a collision can occur.
 */
public interface Collidable {
    /**
     * getCollisionRectangle return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          the hitter ball.
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the velocity of the object.
     * @return return is the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * intersectionPoints method return a list of the intersection point between Collidable object
     * and the line.
     *
     * @param line line to check if intersection with.
     * @return list of intersection point between Collidable and the line.
     */
    java.util.List<Point> intersectionPoints(Line line);
}