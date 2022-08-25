import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //field for rectangle.
    private Rectangle rectangle;
    //list of listeners.
    private List<HitListener> hitListeners;


    /**
     * Block constructor.
     *
     * @param rectangle object that create as block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new LinkedList<HitListener>();
    }

    /**
     * getCollisionRectangle return the rectangle field of the Block.
     *
     * @return rectangle field.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * intersectionPoints method return a List object that contain the intersection points of line with the Block.
     *
     * @param line line to check if intersection with.
     * @return list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        return this.rectangle.intersectionPoints(line);
    }

    /**
     * drawOn method draw the block on the DrawSurface object.
     *
     * @param d DrawSurface that need to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    /**
     * timePassed method for the block object.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * addToGame method add the block as sprite and Collidable objects to the gameLevel.
     *
     * @param gameLevel the gameLevel object that should use block.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * remove a block from the gameLevel.
     *
     * @param gameLevel the current gameLevel.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify all the HitListener that hit occurs.
     *
     * @param hitter the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        //temp velocity.
        Velocity velocity = currentVelocity;
        //epsilon to equation.
        double epsilon = Math.pow(10, -5);
        //the collision point is on the side's edges of the block, change direction in x axes.
        if (Math.abs(collisionPoint.getX() - rectangle.getUpperLeft().getX()) < epsilon
                || Math.abs(collisionPoint.getX() - rectangle.getUpperLeft().getX() - rectangle.getWidth()) < epsilon) {
            velocity.setDx((-1) * velocity.getDx());
        }
        //the collision point is on the upper/bottom side of the block, change direction in y axes.
        if (Math.abs(collisionPoint.getY() - this.rectangle.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY() - rectangle.getUpperLeft().getY() - rectangle.getHeight())
                < epsilon) {
            velocity.setDy((-1) * velocity.getDy());
        }
        return velocity;
    }
}
