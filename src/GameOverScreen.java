import biuoop.DrawSurface;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class GameOverScreen implements Animation {
    //counter of the score.
    private Counter counter;

    /**
     * Constructor of the GameOverScreen.
     *
     * @param counter score counter.
     */
    public GameOverScreen(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 5, d.getHeight() / 3, "Game Over. Your score is " + this.counter.getValue(),
                30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public double framePerSec() {
        return 0;
    }
}
