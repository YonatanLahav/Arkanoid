/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class ScoreTrackingListener implements HitListener {
    //The score counter of the game.
    private Counter currentScore;

    /**
     * Constructor of ScoreTrackingListener.
     *
     * @param counter specific score counter of the game.
     */
    public ScoreTrackingListener(Counter counter) {
        this.currentScore = counter;
    }

    /**
     * Some block have been hit, increase the score by 5 points.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);

    }
}