import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class KeyPressStoppableAnimation implements Animation {
    //Sensor of keyboard.
    private KeyboardSensor sensor;
    //should stop boolean.
    private boolean stop;
    //the animation.
    private Animation animation;
    //string as key.
    private String key;
    //flag if the sensor already pressed.
    private boolean isAlreadyPressed;

    /**
     * KeyPressStoppableAnimation Constructor.
     *
     * @param keyboardSensor sensor that stop animation.
     * @param key            key of the animation.
     * @param animation      the animation to implements.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.sensor = keyboardSensor;
        this.stop = false;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public double framePerSec() {
        return 60;
    }
}
