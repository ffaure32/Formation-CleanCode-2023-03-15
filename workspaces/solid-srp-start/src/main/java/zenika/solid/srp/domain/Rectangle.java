package zenika.solid.srp.domain;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class Rectangle
{
    private final Coords topLeft, bottomRight;

    public Rectangle(Coords topLeft, Coords bottomRight) {
        this.topLeft = requireNonNull(topLeft);
        this.bottomRight = requireNonNull(bottomRight);
        if(width() <= 0) throw new IllegalArgumentException(format(
                "topLeft(%s) is not to the left of bottomRight(%s)",
                topLeft, bottomRight
        ));
        if(heigth() <= 0) throw new IllegalArgumentException(format(
                "topLeft(%s) is not to the top of bottomRight(%s)",
                topLeft, bottomRight
        )); 
    }

    public int perimeter() {
        return 2 * (width() + heigth());
    }

    public int area() {
        return width() * heigth();
    }

    private int width() {
        return bottomRight.x - topLeft.x;
    }

    public int heigth() {
        return topLeft.y - bottomRight.y;
    }

    public Coords getTopLeft() {
        return topLeft;
    }

    public Coords getBottomRight() {
        return bottomRight;
    }
}
