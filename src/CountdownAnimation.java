import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class CountdownAnimation implements Animation {

    //field of the total time to count.
    private double numOfSeconds;
    //count from this int.
    private int countFrom;
    //sprite to display on th screen.
    private SpriteCollection gameScreen;

    /**
     * Constractor of CountdownAnimation.
     *
     * @param numOfSeconds field of the total time to count.
     * @param countFrom    count from this number.
     * @param gameScreen   the sprites of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }


    /**
     * draw one frame on the DrawSurface object.
     *
     * @param d surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 2 - 50, d.getHeight() / 2, String.valueOf(countFrom), 100);
        this.countFrom = this.countFrom - 1;

    }

    /**
     * stop the animation.
     *
     * @return false if keep draw, else true.
     */
    public boolean shouldStop() {
        return !(countFrom > 0);
    }

    @Override
    public double framePerSec() {
        return countFrom / numOfSeconds;
    }

}