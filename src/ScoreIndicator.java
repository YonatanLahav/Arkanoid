import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class ScoreIndicator implements Sprite {
    //rectangle of the scores bar.
    private Rectangle rectangle;
    //score counter.
    private Counter scoreCounter;

    /**
     * Constructor for ScoreIndicator.
     *
     * @param counter Counter for the scores at the current game.
     */
    public ScoreIndicator(Counter counter) {
        //create the bar area.
        this.rectangle = new Rectangle(new Point(0, 0), 800, 20, Color.lightGray);
        this.scoreCounter = counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //draw and fill the score bar rectangle on d.
        d.setColor(this.rectangle.getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        //draw the text of the score bar.
        d.setColor(Color.black);
        d.drawText(350, 15, "score: " + scoreCounter.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add the ScoreIndicator to the gameLevel as a sprite.
     *
     * @param gameLevel the current gameLevel.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
