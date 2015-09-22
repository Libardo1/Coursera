import java.util.ArrayList;
import java.util.Arrays;


public class BruteCollinearPoints {
    
    
    
    private int count;
    private LineSegment[] linesegments;
    
    public BruteCollinearPoints(Point[] points) {
        ArrayList<LineSegment> temp =  new ArrayList<LineSegment>();
        if (points == null) throw new java.lang.NullPointerException();
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
                        count++;
                       
                        LineSegment lineSegment = new LineSegment(p, s);
                        temp.add(lineSegment);
                     }
                  } 
                }
            }
        }
        linesegments = temp.toArray(new LineSegment[temp.size()]);   
    }
    
    private boolean checkRepeats(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            if ((i != points.length - 1) 
               && (points[i].compareTo(points[i + 1])) == 0)
            {
                return true;
            }
            else if (i == points.length -1) {
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
        return linesegments;
        
        
    }


}



