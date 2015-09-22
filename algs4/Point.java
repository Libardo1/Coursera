/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertcal;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        int x0 = this.x;
        int y0 = this.y;
        
        int x1 = that.x;
        int y1 = that.y;
        
        if ((x1 == x0) && (y1 == y0)){
            return Double.NEGATIVE_INFINITY;
        }
        
        if (x1 == x0){
            return Double.POSITIVE_INFINITY;
        }
        else if (y0 == y1){
            return 0.0;
        }
        return ((double)(y1 - y0)) / (x1 - x0);
        /* YOUR CODE HERE */
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        int x0 = this.x;
        int y0 = this.y;
        
        int x1 = that.x;
        int y1 = that.y;
        if ((x0 == x1) && (y0== y1)) { 
            return 0;
        }
        else if ((y0 < y1) || (y0 == y1) && (x0 < x1)) {
           return -1; 
        }
        else { return 1; }
        
            
            
        /* YOUR CODE HERE */
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    
    private class SlopeOrderCode implements Comparator<Point>
    {
        public int compare(Point q1, Point q2) {
            
            double slope1 = Point.this.slopeTo(q1);
            double slope2 = Point.this.slopeTo(q2);
   
            if (slope1 < slope2) return -1;
            if (slope2 < slope1) return 1;
            return 0;
        }
        
    }
    public static Comparator<Point> slopeOrder() {
        return new SlopeOrderCode();
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Throws an exception if called. The hashCode() method is not supported because
     * hashing has not yet been introduced in this course. Moreover, hashing does not
     * typically lead to good *worst-case* performance guarantees, as required on this
     * asssignment.
     *
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        /* DO NOT MODIFY */
        throw new UnsupportedOperationException();
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}