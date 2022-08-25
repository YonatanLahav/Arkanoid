import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class ThirdLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 25;
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
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Disco Disco";
    }

    @Override
    public Sprite getBackground() {
        return new ThirdLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        //the amount of the first row block.
        int firstRowAmount = 12;
        //the block's length.
        int blockLength = 60;
        //the block's width.
        int blockWidth = 20;
        Random random = new Random();
        //calculate the upper left x coordinate of the first block in the first row.
        int firstBlock = 800 - (firstRowAmount * blockLength) - 20;
        //generate blocksAmount-1 in each run of the loop.
        for (int i = 20 * 2, row = 0; i <= firstRowAmount * blockLength; i += blockWidth, row++) {
            //set a different color for each row of blocks.
            Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            //generate the blocks of each row.
            for (int j = firstBlock + row * blockLength; j <= 800 - blockLength; j += blockLength) {
                //crate the blocks.
                blocks.add(new Block(new Rectangle(new Point(j, i), blockLength, blockWidth, color)));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 78;
    }
}
