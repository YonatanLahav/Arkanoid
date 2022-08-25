import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class FirstLevelBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        int x = 400, y = 200;
        d.setColor(Color.darkGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.RED);
        d.drawCircle(x, y, 120);
        d.drawCircle(x, y, 95);
        d.drawCircle(x, y, 70);
        d.drawCircle(x, y, 45);
        d.drawLine(x, y, x, y - 120);
        d.drawLine(x, y, x, y + 120);
        d.drawLine(x, y, x - 120, y);
        d.drawLine(x, y, x + 120, y);
    }

    @Override
    public void timePassed() {
    }
}
