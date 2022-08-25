import java.util.ArrayList;
import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class GameEnvironment {
    //list of the collidables objects.
    private List<Collidable> collidables;

    /**
     * constructor to GameEnvironment object.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * addCollidable method add the given Collidable to the GameEnvironment.
     *
     * @param c the Collidable object that need to add to the list.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * getClosestCollision method return the closest collision between the trajectory and the object in
     * collidables as CollisionInfo object. if there isn't any collide between the line and
     * collidables, return null.
     *
     * @param trajectory the line that need to check if collibe with collidables objects.
     * @return CollisionInfo for the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //new list of collisionInfo.
        List<CollisionInfo> collisionInfoList = new ArrayList<>();
        List<Collidable> tempCollidable = new ArrayList<>(this.collidables);
        //check for ant collidable in collidables if the is any collide with trajection.
        for (Collidable collidable : tempCollidable) {
            //if there isn't ant collide, continue to the next collidable object in the list.
            if (collidable.intersectionPoints(trajectory) != null) {
                //there is collide, add him as collisionInfo object to collisionInfoList with the closest intersection.
                collisionInfoList.add(new CollisionInfo(collidable,
                        trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle())));
            }
        }
        //if there isn't any collide, return null. otherwise, return the closest collisionInfo to trajectory line.
        return (collisionInfoList.isEmpty()) ? null : trajectory.closestIntersectionToStartOfLine(collisionInfoList);
    }

    /**
     * getCollidables method return the collidables field of the GameEnvironment object.
     *
     * @return collidables field.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }
}