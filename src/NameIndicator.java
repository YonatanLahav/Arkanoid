import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class NameIndicator implements Sprite {
    //Name of the level.
    private String name;

    /**
     * Constructor of NameIndicator.
     *
     * @param name the name of the level.
     */
    public NameIndicator(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(0, 15, "Level Name: " + name, 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add the object as sprite to show on the screen.
     *
     * @param g the gameLevel.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
