import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class ForthLevel implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 50;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Random random = new Random();
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(random.nextInt(360), 10));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 3;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Bubble";
    }

    @Override
    public Sprite getBackground() {
        return new ForthLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        Random random = new Random();
        List<Block> blocks = new LinkedList<>();
        for (int i = 200; i <= 290; i += 30) {
            for (int j = 40; j <= 720; j += 60) {
                blocks.add(new Block(new Rectangle(new Point(j, i), 60, 20,
                        new Color(200 + random.nextInt(50), 200 + random.nextInt(50), 255))));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 48;
    }
}
