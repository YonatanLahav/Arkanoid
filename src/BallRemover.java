/**
 * @author Yonatan Lahav
 */
public class BallRemover implements HitListener {

    //The current gameLevel.
    private GameLevel gameLevel;
    //remain balls counter.
    private Counter remainingBalls;

    /**
     * Constructor for BallRemover.
     *
     * @param gameLevel         the current gameLevel.
     * @param removedBalls counter of balls.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * when hit occur, remove the ball from the gameLevel and decrease the counter by 1.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the hitter ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
