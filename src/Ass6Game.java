import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Ass6Game {
    /**
     * the main method create and run a game.
     *
     * @param args empty.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new LinkedList<>();
        List<LevelInformation> stages = new ArrayList<>();
        stages.add(new FirstLevel());
        stages.add(new SecondLevel());
        stages.add(new ThirdLevel());
        stages.add(new ForthLevel());

        if (args.length != 0) {
            for (String arg : args) {
                try {
                    int num = Integer.parseInt(arg);
                    if (num > 0 && num < 5) {
                        levels.add(stages.get(num - 1));
                    }
                } catch (Exception exception) {

                }
            }
        }
        if (levels.isEmpty()) {
            levels.addAll(stages);
        }
        AnimationRunner animationRunner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(animationRunner, animationRunner.getGui().getKeyboardSensor());
        gameFlow.runLevels(levels);

    }
}
