import java.util.List;

/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Line {
    //The start Point of the line.
    private Point start;
    //The end Point of the line.
    private Point end;
    //constant variable represented Counter Clockwise orientation.
    private static final int COUNTER_CLOCKWISE = 1;
    //constant variable represented Collinear orientation.
    private static final int COLLINEAR = 0;
    //constant variable represented Clockwise orientation.
    private static final int CLOCKWISE = -1;

    /**
     * Constructor for Line objects.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for Line objects.
     *
     * @param x1 the start X-Axis coordinate.
     * @param y1 the start Y-Axis coordinate.
     * @param x2 the end X-Axis coordinate.
     * @param y2 the end Y-Axis coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * lenght method Calculates the segment size.
     *
     * @return returm double bariable that represent the lenght of the segment.
     */
    public double length() {
        return Math.sqrt(Math.pow(this.start.getX() - this.end.getX(), 2)
                + Math.pow(this.start.getY() - this.end.getY(), 2));
    }

    /**
     * middle method calculate the middle point of the segment.
     *
     * @return the middle point as Point object.
     */
    public Point middle() {
        //deltaX divide by 2 for the middle x coordinate.
        double midX = (this.start.getX() + this.end.getX()) / 2;
        //deltaY divide by 2 for the middle y coordinate.
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * start method return the start point of the segment.
     *
     * @return start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * end method return the end point of the segment.
     *
     * @return end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting method return true if the 2 segments are intersecting or have a common segment between them,
     * otherwise, return false if the segments aren't intersecting.
     *
     * @param other the second line for check if there is intersection with.
     * @return true for intersection or common segment, otherwise false.
     */
    public boolean isIntersecting(Line other) {
        //orientesion between this line and other start point.
        int thisWithOtherStart = this.orientation(other.start);
        //orientesion between this line and other end point.
        int thisWithOtherEnd = this.orientation(other.end);
        //orientesion between other line and this start point.
        int otherWithThisStart = other.orientation(this.start);
        //orientesion between other line and this end point.
        int otherWithThisEnd = other.orientation(this.end);
        //there is intersection point or common segment by the 4 orientetion of the segments.
        if ((thisWithOtherStart * thisWithOtherEnd <= 0) && (otherWithThisStart * otherWithThisEnd) <= 0) {
            //return true.
            return true;
        }
        //return false.
        return false;
    }

    /**
     * intersectionWith method calculate the intersection point of the two lines.
     *
     * @param other the other line for the intersection.
     * @return if there is intersection Point, return it, otherwise, there isn't intersection point so return null.
     */
    public Point intersectionWith(Line other) {
        //This line represent as a1x+b1y=c1.
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = (a1 * this.start.getX()) + (b1 * this.start.getY());
        //Other line represent as a2x+b2y=c2.
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = (a2 * other.start.getX()) + (b2 * other.start.getY());
        //determinant of the segment slopes.
        double determinant = a1 * b2 - a2 * b1;
        //Min/Max values this line and other line.
        double minYThis = Math.min(this.start.getY(), this.end.getY());
        double maxYThis = Math.max(this.start.getY(), this.end.getY());
        double minYOther = Math.min(other.start.getY(), other.end.getY());
        double maxYOther = Math.max(other.start.getY(), other.end.getY());
        double minXThis = Math.min(this.start.getX(), this.end.getX());
        double maxXThis = Math.max(this.start.getX(), this.end.getX());
        double minXOther = Math.min(other.start.getX(), other.end.getX());
        double maxXOther = Math.max(other.start.getX(), other.end.getX());
        //there is intersection by isIntersecting, need to check for common segment or single intersection point.
        if (this.isIntersecting(other)) {
            //there isn't any intersection between two different points, return null.
            if (this.length() == 0 && other.length() == 0 && !this.start.equals(other.start)) {
                return null;
            }
            //if the lines are the same line or opposite line, return null.
            if (this.equals(other) && (this.length() != 0 || other.length() != 0)) {
                return null;
            }
            //one of the segment is a Point, return the intersection point.
            if (this.start.equals(this.end) || other.start.equals(other.end)) {
                return (this.start.equals(this.end)) ? this.start : other.end;
            }
            //the lines are parallel.
            if (determinant == 0) {
                //the lines have a common y coordinate inside each other, hence they have a common segment.
                if ((maxYThis > minYOther && minYOther > minYThis)
                        || (maxYOther > minYThis && minYThis > minYOther)
                        || (maxYThis > maxYOther && maxYOther > minYThis)
                        || (maxYOther > maxYThis && maxYThis > minYOther)) {
                    //common segment, return null.
                    return null;
                }
                //The lines are horizontals, check if there is common segment between them.
                if (this.start.getY() - this.end.getY() == 0) {
                    //the lines have a common x coordinate inside each other, hence they have a common segment.
                    if ((maxXThis > minXOther && minXOther > minXThis)
                            || (maxXOther > minXThis && minXThis > minXOther)
                            || (maxXThis > maxXOther && maxXOther > minXThis)
                            || (maxXOther > maxXThis && maxXThis > minXOther)) {
                        //common segment, return null.
                        return null;
                    }
                }
                //the segments are different and there isn't common segment, return the intersection point.
                if (!this.equals(other) && !(new Line(this.end, this.start).equals(other))) {
                    //if this.start on equal to other end, return it. otherwise return other.end.
                    return (this.start.equals(other.start) || this.end.equals(other.start)) ? other.start : other.end;
                }
            }
            return new Point((b2 * c1 - b1 * c2) / determinant, (a1 * c2 - a2 * c1) / determinant);
        }
        return null;
    }

    /**
     * equals method check whether the lines are equals or not.
     *
     * @param other line that should check if equal to this line.
     * @return if the lines are equals, return true. otherwise return false.
     */
    public boolean equals(Line other) {
        //return true if the lines are equals.
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start)));
    }

    /**
     * orientation method return what is the orientation of the line with point.
     * if the line and point are counter clockwise, return 1.
     * if the line and point are collinear, return 0.
     * if the line and point are clockwise, return -1.
     *
     * @param point Point object to calculate the orientation with.
     * @return int variable, counter clockwise=1, collinear=0, clockwise=-1.
     */
    public int orientation(Point point) {
        //variables for the delta between points.
        double deltaXLine = (this.end.getX() - this.start.getX());
        double deltaYLine = (this.end.getY() - this.start.getY());
        double deltaXPointStart = (point.getX() - this.start.getX());
        double deltaYPointStart = (point.getY() - this.start.getY());
        //represent the equation between the slope of the line and the slope of start to point.
        double slopesResult = (deltaXPointStart * deltaYLine) - (deltaYPointStart * deltaXLine);
        //the slopes aren't collinear.
        if (slopesResult != 0.0) {
            return (slopesResult > 0.0) ? CLOCKWISE : COUNTER_CLOCKWISE;
        }
        //the slopes are collinear.
        slopesResult = deltaXPointStart * deltaXLine + deltaYPointStart * deltaYLine;
        /*
        need to check on which side the point is. determine it with point's projection.
        if the slopesResult are negative, so the point projection outside the segment.
         */
        if (slopesResult > 0.0) {
            //reverse the projection.
            slopesResult = (deltaXPointStart - deltaXLine) * deltaXLine + (deltaYPointStart - deltaYLine) * deltaYLine;
            //the orientation changed, hence they are collinear.
            if (slopesResult < 0.0) {
                //change the slopesResult to 0.0 because the lines have common segment.
                slopesResult = 0.0;
            }
        }
        if (slopesResult > 0.0) {
            //return COUNTER_CLOCKWISE orientation.
            return COUNTER_CLOCKWISE;
        } else if (slopesResult == 0.0) {
            //return COLLINEAR orientation.
            return COLLINEAR;
        } else {
            //return CLOCKWISE orientation.
            return CLOCKWISE;
        }
    }

    /**
     * closestIntersectionToStartOfLine method return the closect intersection Point between the line object
     * and the givven rectangle object.
     * if there isn't any intersection between them, return null.
     * otherwise, there is intersection, so return the closest one to the start.
     *
     * @param rect rectangle object to check intersection with.
     * @return the closect intersection point between them, if there isn't, return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //create a list of intersection point between them.
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        //if there is any intersection, return null.
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        //there is at list one intersection, create temporary Point.
        Point closestPoint = intersectionPoints.get(0);
        //loop that run on all the intersection point between the rectangle and the line.
        for (Point point : intersectionPoints) {
            //if the point is closest then the closest point before it, make it the closest one.
            closestPoint = (this.start.distance(point) < this.start.distance(closestPoint)) ? point : closestPoint;
        }
        //return the point.
        return closestPoint;
    }

    /**
     * closestIntersectionToStartOfLine get a list of CollisionInfo and return the CollisionInfo object
     * that it is the closest one to the start point of the line.
     * if the CollisionInfoList is empty, return null.
     *
     * @param collisionInfoList the CollisionInfos object as list.
     * @return the closest CollisionInfo object to the start point of the line.
     */
    public CollisionInfo closestIntersectionToStartOfLine(List<CollisionInfo> collisionInfoList) {
        //if the list is empty, return null.
        if (collisionInfoList.isEmpty()) {
            return null;
        }
        //the list isn't empty. choose the first element in the list to be the closest one.
        CollisionInfo closestCollision = collisionInfoList.get(0);
        //double variable that contain the closest distance until now.
        double closestDistance;
        //for each element in the list, check if it is more close to start then the closest one until now.
        for (CollisionInfo collisionInfo : collisionInfoList) {
            closestDistance = this.start.distance(closestCollision.collisionPoint());
            //if collisionInfo is closer, turn him to be the closestCollision.
            closestCollision = (this.start.distance(collisionInfo.collisionPoint()) < closestDistance)
                    ? collisionInfo : closestCollision;
        }
        return closestCollision;
    }
}