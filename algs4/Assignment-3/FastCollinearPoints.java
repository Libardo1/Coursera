import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
    
public class FastCollinearPoints {
    private int count;
    private LineSegment[] linesegments;
    
     public FastCollinearPoints(Point[] pointsArray)  {
        //Arrays.sort(points);
        ArrayList<LineSegment> temp =  new ArrayList<LineSegment>();
        if (pointsArray == null) throw new java.lang.NullPointerException();
        Point[] sortedPoints = new Point[pointsArray.length];
        System.arraycopy(pointsArray, 0, sortedPoints, 0, sortedPoints.length);
        Arrays.sort(sortedPoints);
        Point[] points = sortedPoints;
        if (checkRepeats(points)) throw new java.lang.IllegalArgumentException();
        
        for (int i = 0; i < points.length -1; i++) {
            Point p = points[i];
            Set<Double> prevSlopes = new HashSet<Double>();
            for (int k = i - 1; k >= 0; k--) {
                double slope = points[k].slopeTo(p);
                prevSlopes.add((Double) slope);
            }
            Point[] slopeOrderedPoints = new Point[points.length -i -1];
            System.arraycopy(
                    points, i + 1, slopeOrderedPoints, 0, points.length - i - 1);
            Arrays.sort(slopeOrderedPoints, p.slopeOrder());
            
            
            double lastSlope = p.slopeTo(slopeOrderedPoints[0]);
            int countPoint = 1;
            int startIndex = 0;     
            for (int index = 1; index < slopeOrderedPoints.length; index++) {
                double currentSlope =  p.slopeTo(slopeOrderedPoints[index]);
                //System.out.println("" + lastSlope + "   " + currentSlope);
                
                if (Double.compare(currentSlope, lastSlope) == 0) { 
                    countPoint++;
                           
                }
                else {
                    if (countPoint >= 3) {
                      
                        
                        if (!prevSlopes.contains((Double) lastSlope)) {
                            
                            LineSegment lineSegment = createSegement(
                            slopeOrderedPoints, p, startIndex, countPoint);
                            temp.add(lineSegment);
                            count++;
                        }
                        
                    }
                    startIndex = index;
                    lastSlope =  currentSlope;
                    countPoint = 1;
                }
            }
            if (countPoint >= 3) {
                if (!prevSlopes.contains((Double) lastSlope)) {
                    LineSegment lineSegment = createSegement(
                                 slopeOrderedPoints, p, startIndex, countPoint);
                    temp.add(lineSegment);
                    count++;
                }
            }
            
        }
// finds all line segments containing 4 or more points
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
    
    private LineSegment createSegement(Point[] array, 
                                       Point p, int startIndex, int countPoint) {
        //Point[] allPoints = new Point[countPoint];
        //allPoints[0] = p;
        //int temp = startIndex;
        //for (int i = 1; i < allPoints.length; i++) {
        //    allPoints[i] = array[startIndex + i];
        //    temp++;
        //}
        //Arrays.sort(allPoints);
        //LineSegment lineSegment = 
        //    new LineSegment(allPoints[0], allPoints[countPoint]);
        LineSegment lineSegment = 
            new LineSegment(p, array[startIndex + countPoint-1]);
        return lineSegment;
    }
    
   

    public int numberOfSegments() {
        return count;
    // the number of line segments
    }
    
    public LineSegment[] segments() {
    LineSegment[] lineSegmentsCopy = new LineSegment[linesegments.length];
    System.arraycopy(linesegments, 0, lineSegmentsCopy, 0, linesegments.length);
    return lineSegmentsCopy;
        
        
    }
    
     public static void main(String[] args) {

    // read the N points from a file
    In in = new In(args[0]);
    int N = in.readInt();
    Point[] points = new Point[N];
    for (int i = 0; i < N; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.show(0);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    //System.out.println("starting ...........");
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    System.out.println("done ......."   + collinear.segments().length);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
     }
}
