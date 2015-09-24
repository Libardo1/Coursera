import java.util.ArrayList;
import java.util.Arrays;


public class BruteCollinearPoints {
    
    private final int count;
    private final LineSegment[] linesegments;
    
    public BruteCollinearPoints(Point[] pointsArray) {
        ArrayList<LineSegment> temp =  new ArrayList<LineSegment>();
        if (pointsArray == null) throw new java.lang.NullPointerException();
        Point[] sortedPoints = new Point[pointsArray.length];
        System.arraycopy(pointsArray, 0, sortedPoints, 0, sortedPoints.length);
        Arrays.sort(sortedPoints);
        Point[] points = sortedPoints;
        
        int numLineSegments = 0;
        
        if (checkRepeats(points)) throw new java.lang.IllegalArgumentException();
        for (int first = 0; first < points.length - 3; first++) {
            Point p = points[first];
            for (int second = first + 1; second < points.length - 2; second++) {
                Point q = points[second];
                double slope1 = p.slopeTo(q);
                
                for (int third = second + 1; third < points.length - 1; third++) {
                    Point r = points[third];
                    double slope2 = p.slopeTo(r);
                    int res = Double.compare(slope1, slope2);
                    if (res != 0) { continue; }
                    for (int forth = third + 1; forth < points.length; forth++) {
                    Point s = points[forth];
                    double slope3 = p.slopeTo(s);
                    int res1 = Double.compare(slope1, slope3);
                    if (res1 == 0) {
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



