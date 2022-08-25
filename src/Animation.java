
import biuoop.DrawSurface;

/**
 * @author Yonatan Lahav.
 * ID 316099548.
 */
public interface Animation {
    /**
     * Draw one frame of the animation on the surface d.
     *
     * @param d surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * is the animation should stop or not.
     *
     * @return if true, stop animation. else, continue.
     */
    boolean shouldStop();

    /**
     * frame per seconds of the animation.
     *
     * @return the number of frame per second.
     */
    double framePerSec();
}