import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class SecondLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 8;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Random random = new Random();
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(random.nextInt(360), 15));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 300;
    }

    @Override
    public String levelName() {
        return "Here comes the sun";
    }

    @Override
    public Sprite getBackground() {
        return new SecondLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        Random random = new Random();
        List<Block> blocks = new LinkedList<>();
        for (int i = 50; i < 700; i += 70) {
            blocks.add(new Block(new Rectangle(new Point(i, 250), 60, 20,
                    new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)))));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 10;
    }
}
