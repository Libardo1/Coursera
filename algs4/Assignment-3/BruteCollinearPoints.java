import java.util.ArrayList;
import java.util.Arrays;


public class BruteCollinearPoints {
    
    private final int count;
    private final LineSegment[] linesegments;
    
    public BruteCollinearPoints(Point[] points) {
        ArrayList<LineSegment> temp =  new ArrayList<LineSegment>();
        if (points == null) throw new java.lang.NullPointerException();
        Point[] sortedPoints = new Point[points.length];
        System.arraycopy(points, 0, sortedPoints, 0, sortedPoints.length);
        Arrays.sort(sortedPoints);
        points = sortedPoints;
        
        int numLineSegments = 0;
        
        if (checkRepeats(points)) throw new java.lang.IllegalArgumentException();
        for (int first = 0; first < points.length; first++) {
            for (int second = first + 1; second < points.length; second++) {
                for (int third = second + 1; third < points.length; third++) {
                    for (int forth = third + 1; forth < points.length; forth++) {
                    Point p = points[first];
                    Point q = points[second];
                    Point r = points[third];
                    Point s = points[forth];
                    double slope1 = p.slopeTo(q);
                    double slope2 = p.slopeTo(r);
                    double slope3 = p.slopeTo(s);
                    
                    int res = Double.compare(slope1, slope2);
                    int res1 = Double.compare(slope1, slope3);
                    
                    if ((res == 0) && (res1 == 0)) {
                    //if ((slope1 == slope2) && (slope1 == slope3)) {
                        numLineSegments++;
                       
                        LineSegment lineSegment = new LineSegment(p, s);
                        temp.add(lineSegment);
                     }
                  } 
                }
            }
        }
        count = numLineSegments;
        linesegments = temp.toArray(new LineSegment[temp.size()]);   
    }
    
    private boolean checkRepeats(Point[] temp) {
        for (int i = 0; i < temp.length; i++) {
            if ((i != temp.length - 1) 
               && (temp[i].compareTo(temp[i + 1])) == 0)
            {
                return true;
            }
            else if (i == temp.length -1) {
                return false;
            }
            
        }
        return false;
    }
    
    
    public int numberOfSegments() {
        return count;
    // the number of line segments
    }
    
    public LineSegment[] segments() {
    // the line segments
        LineSegment[] lineSegmentsCopy = new LineSegment[linesegments.length];
        System.arraycopy(linesegments, 0, lineSegmentsCopy, 0, linesegments.length);
        return lineSegmentsCopy;
    }


}



