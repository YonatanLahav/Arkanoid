/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class BlockRemover implements HitListener {
    //the current gameLevel.
    private GameLevel gameLevel;
    //the remain blocks in the gameLevel.
    private Counter remainingBlocks;

    /**
     * constructor for BlockRemoval.
     *
     * @param gameLevel          the current gameLevel.
     * @param removedBlocks the remains blocks in the gameLevel.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the gameLevel.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}