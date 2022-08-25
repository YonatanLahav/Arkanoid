import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Rectangle {
    //the upper left point of the rectangle.
    private Point upperLeft;
    //the width of the rectangle.
    private double width;
    //the height of the rectangle.
    private double height;
    //the color of the rectangle.
    private Color color;

    /**
     * Rectangle constractor.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = Color.lightGray;
    }

    /**
     * Rectangle constractor.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     * @param color     the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * rectangleEdges method return a Line array of the edge of the rectangle.
     *
     * @return line array that containe the rectangle edges.
     */
    public Line[] rectangleEdges() {
        //create Line array.
        Line[] lines = new Line[4];
        Point leftUpper, rightUpper, leftDown, rightDown;
        //calculate each corner of the rectangle.
        leftUpper = this.upperLeft;
        rightUpper = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        leftDown = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        rightDown = new Point(leftDown.getX() + width, rightUpper.getY() + height);
        //create new line in lines array that are the edges of the rectangle.
        lines[0] = new Line(leftUpper, rightUpper);
        lines[1] = new Line(leftUpper, leftDown);
        lines[2] = new Line(rightUpper, rightDown);
        lines[3] = new Line(leftDown, rightDown);
        return lines;
    }

    /**
     * intersectionPoints return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line specific line to check if intersect with the rectangle.
     * @return list of intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        //array of the edged of the rectangle.
        Line[] edges = rectangleEdges();
        //list of intersection points.
        List<Point> intersectionList = new ArrayList<>();
        Point intersectionPoint;
        //run on all the edged of the rectangle.
        for (Line edge : edges) {
            //the intersection between the edge and the line.
            intersectionPoint = edge.intersectionWith(line);
            //if there is intersection, add it to the list.
            if (intersectionPoint != null) {
                intersectionList.add(intersectionPoint);
            }
        }
        //if the list isn't empty, return it. otherwise return null.
        return (intersectionList.isEmpty()) ? null : intersectionList;
    }

    /**
     * getHeight method.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * getWidth method.
     *
     * @return the width of the rectangle field.
     */
    public double getWidth() {
        return width;
    }

    /**
     * getColor method.
     *
     * @return the color of the rectangle field.
     */
    public Color getColor() {
        return color;
    }

    /**
     * drawOn method draw the rectangle on the DrawSurface d object.
     *
     * @param d DrawSurface object that need to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor().darker());
        d.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) (this.getWidth()), (int) (this.getHeight()));
        d.setColor(Color.black);
        d.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) (this.getWidth()), (int) (this.getHeight()));
    }

    /**
     * getUpperLeft method.
     *
     * @return the getUpperLeft field of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
}