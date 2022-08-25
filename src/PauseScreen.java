import biuoop.DrawSurface;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 5, d.getHeight() / 3, "paused -- press space to continue", 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public double framePerSec() {
        return 60;
    }
}

