import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class SecondLevelBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Color color;
        int x = 400, y = 200;
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        for (int i = 800; i > 0; i -= 10) {
            d.drawLine(90, 110, i, 250);
        }
        color = Color.orange.darker().darker();
        for (int i = 60; i > 0; i -= 5) {
            d.setColor(color);
            d.fillCircle(90, 110, i);
            color = color.brighter();
        }
    }

    @Override
    public void timePassed() {
    }
}


