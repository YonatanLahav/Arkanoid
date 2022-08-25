/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class CollisionInfo {
    //field for collision point.
    private Point collisionPoint;
    //field for collision object.
    private Collidable collisionObject;

    /**
     * CollisionInfo constructor.
     *
     * @param collisionObject the collision object.
     * @param collisionPoint  the collision point.
     */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * collisionPoint return the point at which the collision occurs.
     *
     * @return collisionPoint field.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * collisionObject method return the collidable object involved in the collision.
     *
     * @return collisionObject field.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}