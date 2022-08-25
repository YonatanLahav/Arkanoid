import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private biuoop.KeyboardSensor keyboardSensor;
    private Counter scoreCounter;

    /**
     * Constructor for GameFlow.
     *
     * @param animationRunner AnimationRunner object of the game.
     * @param keyboardSensor  the keyboard sensor.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.scoreCounter = new Counter(0);
    }

    /**
     * run the provided levels.
     *
     * @param levels list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        //flag for win or loose.
        boolean win = true;
        //create the game level from the list.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.scoreCounter);
            level.initialize();
            //keep running the provided level while the level is running.
            while (level.isRunning()) {
                level.run();
            }
            //there isn't more balls. gameOver.
            if (level.getBallCounter().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new GameOverScreen(this.scoreCounter)));
                win = false;
                break;
            }
        }
        //flag is still win and no more levels left, winner !
        if (win) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new WinScreen(this.scoreCounter)));

        }
        this.animationRunner.getGui().close();
    }
}