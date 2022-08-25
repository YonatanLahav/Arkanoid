import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class ThirdLevelBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Random random = new Random();
        Color color;
        int x = 400, y = 200;
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 600);
        for (int i = 100; i > 0; i--) {
            d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            d.drawLine(400, 400, random.nextInt(800), random.nextInt(600));
        }
    }

    @Override
    public void timePassed() {
    }
}

