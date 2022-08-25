import biuoop.DrawSurface;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class WinScreen implements Animation {
    private Counter counter;

    /**
     * Constructor of WinScreen.
     *
     * @param counter counter of the score.
     */
    public WinScreen(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 5, d.getHeight() / 3, "You Win! Your score is " + this.counter.getValue(),
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
