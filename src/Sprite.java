import biuoop.DrawSurface;

/**
 * @author Yonatan Lahav.
 * ID 316099548.
 * Sprite interface represents object that can be drawn to the screen.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface that need to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}