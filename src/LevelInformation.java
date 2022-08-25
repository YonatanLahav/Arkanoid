import java.util.List;

/**
 * @author Yonatan Lahav.
 * ID 316099548.
 */
public interface LevelInformation {
    /**
     * Number of balls in this level.
     *
     * @return the numbers of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return List of Velocities for the ball in the level.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * The paddle width.
     *
     * @return The paddle width.
     */
    int paddleWidth();

    /**
     * The level name.
     *
     * @return String of the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     *
     * @return List of Block in this level.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed,before the level is considered to be "cleared".
     *
     * @return Number of the blocks in the level.
     */
    int numberOfBlocksToRemove();
}