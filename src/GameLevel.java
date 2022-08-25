import biuoop.DrawSurface;

import java.awt.Color;


/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class GameLevel implements Animation {
    //all the sprites in the game.
    private SpriteCollection sprites;
    //the game environment of the game.
    private GameEnvironment environment;
    //the width of the frame blocks.
    private static final int FRAME_WIDTH = 20;
    //the width of the screen.
    private static final int SCREEN_WIDTH = 800;
    //the height of the screen.
    private static final int SCREEN_HEIGHT = 600;
    //field of animation runner and boolean for running.
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    //Balls, score and blocks counters.
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    //field for LevelInformation.
    private LevelInformation levelInformation;


    /**
     * Constructor for GameLevel.
     *
     * @param levelInformation the levelInformation of this level.
     * @param keyboard         KeyboardSensor of the game.
     * @param animationRunner  the AnimationRunner to run the anumation.
     * @param counter          score counter.
     */
    public GameLevel(LevelInformation levelInformation, biuoop.KeyboardSensor keyboard,
                     AnimationRunner animationRunner, Counter counter) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.running = true;
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.score = counter;

    }

    /**
     * addCollidable method add a collidable object to the game.
     *
     * @param c collidable object that want to add to the game.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite method add a Sprite object to the game.
     *
     * @param s Sprite object that want to add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.

    /**
     * initialize method initialize a new game,
     * create blocks, ball and paddle.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        NameIndicator nameIndicator = new NameIndicator(this.levelInformation.levelName());
        DrawSurface surface = this.runner.getGui().getDrawSurface();
        this.addSprite(this.levelInformation.getBackground());
        new Paddle(this, this.levelInformation.paddleWidth(), this.levelInformation.paddleSpeed()).addToGame(this);
        this.createBalls();
        this.frameBlocks(ballRemover);
        this.createBlocks();
        scoreIndicator.addToGame(this);
        nameIndicator.addToGame(this);

    }

    /**
     * frameBlocks method create the frame blocks and add them to the game.
     *
     * @param ballRemover BallRemover object for the bottom block.
     */
    private void frameBlocks(BallRemover ballRemover) {
        Block block;
        //frame blocks width.
        int blockWidth = 20;
        //the left frame block
        block = new Block(new Rectangle(new Point(0, 20), blockWidth, SCREEN_HEIGHT - 20));
        block.addToGame(this);
        //the upper frame block.
        block = new Block(new Rectangle(new Point(0, 20), SCREEN_WIDTH, blockWidth));
        block.addToGame(this);
        //bottom frame block.
        block = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT),
                SCREEN_WIDTH, 0));
        block.addToGame(this);
        block.addHitListener(ballRemover);
        //left frame block.
        block = new Block(new Rectangle(new Point(SCREEN_WIDTH - blockWidth, 20),
                blockWidth, SCREEN_HEIGHT - 20));
        block.addToGame(this);
    }

    /**
     * run method run the game and start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * getGui method return the gui field of the GameLevel object.
     *
     * @return gui field of the GameLevel object.
     */

    /**
     * getFrameWidth method return the FRAME_WIDTH field of the GameLevel object.
     *
     * @return FRAME_WIDTH field.
     */
    public int getFrameWidth() {
        return FRAME_WIDTH;
    }

    /**
     * getScreenHeight method return the SCREEN_HEIGHT field of the GameLevel object.
     *
     * @return SCREEN_HEIGHT field of the GameLevel object.
     */
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /**
     * getScreenWidth method return the SCREEN_WIDTH field of the GameLevel object.
     *
     * @return SCREEN_WIDTH field of the GameLevel object.
     */
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * remove Collidable object from the Collidable list in the game environment.
     *
     * @param c the object that need to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * removeSprite method remove the sprite s from the sprites collection list.
     *
     * @param s the sprite should be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);

    }

    /**
     * Getter for the BallCounter.
     *
     * @return the BallCounter field of the game.
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * getter for Runner field.
     *
     * @return the runner field value.
     */
    public AnimationRunner getRunner() {
        return runner;
    }

    /**
     * return if the game is still running.
     *
     * @return running field.
     */
    public boolean isRunning() {
        return running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int removedAllBlocks = 100;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, keyboard.SPACE_KEY, new PauseScreen()));
        }
        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            if (this.blockCounter.getValue() == 0) {
                this.score.increase(removedAllBlocks);
            }
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public double framePerSec() {
        return 60;
    }

    /**
     * Create balls by levelInformation info and add them to the game level.
     */
    private void createBalls() {
        int delta = this.levelInformation.paddleWidth() / this.levelInformation.initialBallVelocities().size();
        double x = (SCREEN_WIDTH / 2 - this.levelInformation.paddleWidth() / 2) + (0.5 * delta);
        for (Velocity velocity : this.levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(new Point(x, SCREEN_HEIGHT - 2.5 * FRAME_WIDTH), 5, Color.black, this.environment);
            ball.setVelocity(velocity);
            x += delta;
            ball.addToGame(this);
        }
    }

    /**
     * Create the blocks of the specific level.
     */
    private void createBlocks() {
        this.blockCounter.increase(this.levelInformation.blocks().size());
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(new BlockRemover(this, this.blockCounter));
            block.addHitListener(new ScoreTrackingListener(score));
        }
    }
}