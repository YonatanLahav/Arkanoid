import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class FirstLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        velocities.add(new Velocity().fromAngleAndSpeed(0, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new FirstLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        blocks.add(new Block(new Rectangle(new Point(370, 185), 60, 30, Color.black)));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
