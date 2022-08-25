import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class SpriteCollection {
    //List of sprites interfaces.
    private List<Sprite> sprites;

    /**
     * SpriteCollection constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * addSprite method add a sprite to the sprite collection filed.
     *
     * @param s the sprite that want to add to the sprites collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tempList = new ArrayList<>(this.sprites);
        for (Sprite sprite : tempList) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d DrawSurface object to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Getter for the spite collection field.
     *
     * @return return the sprites list.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
}