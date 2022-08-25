
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Yonatan Lahav.
 * ID 316099548.
 */
public class AnimationRunner {
    //the width of the screen.
    private static final int SCREEN_WIDTH = 800;
    //the height of the screen.
    private static final int SCREEN_HEIGHT = 600;
    //gui object
    private GUI gui;
    //frame per sec
    private int framesPerSecond;

    /**
     * Constructor for AnimationRunner.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.framesPerSecond = 60;
    }

    /**
     * Getter for gui field.
     *
     * @return gui field of AnimationRunner.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * run the animation.
     *
     * @param animation the animation that need to run.
     */
    public void run(Animation animation) {
        //sleeper for animation.
        Sleeper sleeper = new Sleeper();
        //frame per sec.
        double fps = animation.framePerSec();
        //frame as millisecond.
        double millisecondsPerFrame = 1000 / fps;
        //keep run until the animation should stop is false.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            //create DrawSurface object to draw on.
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            //show d surface.
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = Double.valueOf(millisecondsPerFrame - usedTime).longValue();
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}