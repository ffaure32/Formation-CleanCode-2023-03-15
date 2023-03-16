package zenika.solid.srp.awt;

import zenika.solid.srp.domain.Rectangle;
import zenika.solid.srp.domain.RectangleDrawer;

import java.awt.*;

public class AWTRectangleDrawer implements RectangleDrawer {
    private final Graphics graphics;

    public AWTRectangleDrawer(Graphics graphics) {
        this.graphics = graphics;
    }


    @Override
    public void draw(Rectangle rectangle) {
        //top horizontal line
        graphics.drawLine(
                rectangle.getTopLeft().x, rectangle.getTopLeft().y,
                rectangle.getBottomRight().x, rectangle.getTopLeft().y
        );
        //bottom horizontal line
        graphics.drawLine(
                rectangle.getTopLeft().x, rectangle.getBottomRight().y,
                rectangle.getBottomRight().x, rectangle.getBottomRight().y
        );
        //left vertical line
        graphics.drawLine(
                rectangle.getTopLeft().x, rectangle.getTopLeft().y,
                rectangle.getTopLeft().x, rectangle.getTopLeft().y - rectangle.heigth()
        );
        //right vertical line
        graphics.drawLine(
                rectangle.getBottomRight().x, rectangle.getBottomRight().y - rectangle.heigth(),
                rectangle.getBottomRight().x, rectangle.getBottomRight().y
        );
    }
}