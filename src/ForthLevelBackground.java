import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class ForthLevelBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            Color color = new Color(200 + random.nextInt(50), 200 + random.nextInt(50), 255);
            d.setColor(color);
            d.fillCircle(random.nextInt(800), random.nextInt(500), random.nextInt(80));
        }
    }

    @Override
    public void timePassed() {

    }
}
