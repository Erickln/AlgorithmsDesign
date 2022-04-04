package Analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class SegmentIntersection {
    public final int INNERPOINT = 1;
    public final int ENDPOINT = 2;

    private final int N;
    private Segment[] S;
    private HashMap<Point2D, Intersection> intersections;
    private RedBlackBST<Double, Segment> status;
    private RedBlackBST<Point2D, Segment> events;

    private class Intersection {
        private ArrayList<Integer> segments;
        private ArrayList<Integer> intersectionTypes;
        private final Point2D intersectionPoint;

        public Intersection(Point2D point) {
            segments = new ArrayList<>();
            intersectionTypes = new ArrayList<>();
            intersectionPoint = point;
        }

        public void addSegment(int segment, int intersectionType) {
            segments.add(segment);
            intersectionTypes.add(intersectionType);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Intersection at (%.2f, $.2f)\n",intersectionPoint.x, intersectionPoint.y));
            for (int i = 0; i < segments.size(); i++) {
                sb.append(String.format("Segment %d: %s\n",
                        segments.get(i), intersectionTypes.get(i) == INNERPOINT ? "Innerpoint" : "Endpoint"));
            }
            return sb.toString();
        }

    }

    public SegmentIntersection(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");

        try {
            N = in.readInt();
            S = new Segment[N];
            status = new RedBlackBST<>();
            events = new RedBlackBST<>();
            intersections = new HashMap<>();

            for (int i = 0; i < N; i++) {
                double x1 = in.readDouble();
                double y1 = in.readDouble();
                double x2 = in.readDouble();
                double y2 = in.readDouble();
                Segment s = new Segment(i, new Point2D(x1, y1), new Point2D(x2, y2));
                S[i] = s;
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in SegmentIntersection constructor", e);
        }
        obtainIntersections();
    }

    public void obtainIntersections(){
        /* Initializa event points with segment end points */
        for (Segment s: S) {
            events.put(s.upper(), s);
            events.put(s.lower(), s);
        }
        while (!events.isEmpty()) {
            Point2D p = events.min();
            Segment s = events.get(p);
            events.deleteMin();
            handleEventPoint(s, p);
        }
    }

    private void handleEventPoint(Segment s, Point2D p) {
        if (s.isSinglePoint()) { // intersection event
            StdOut.println("Intersection point " + p.toString());
            updateStatus(s.upper().y);

        } else if (s.upper().equals(p)) { //upper end point event
            StdOut.println("Upper end point " + p.toString());
            //Update the status with correct x coordinates
            updateStatus(p.y);
            /* Get the right and left neighbors of s */
            Segment leftNeighbor = getLeftNeighbor(s);
            Segment rightNeighbor = getRightNeighbor(s);
            /* Add the new segment to the status */
            status.put(s.upper().x, s);
            if (leftNeighbor != null && doIntersect(leftNeighbor, s)) {
                /* Create and add the intersection to the intersection hash table */
                Point2D intrsPoint = getIntersectionPoint(leftNeighbor, s);
                Intersection intersection = new Intersection(intrsPoint);
                intersection.addSegment(leftNeighbor.getId(),
                        (leftNeighbor.upper().equals(intrsPoint) ? ENDPOINT :
                                leftNeighbor.lower().equals(intrsPoint) ? ENDPOINT : INNERPOINT));
                intersection.addSegment(s.getId(),
                        (s.upper().equals(intrsPoint) ? ENDPOINT :
                                s.lower().equals(intrsPoint) ? ENDPOINT : INNERPOINT));

                if (!intersections.containsKey(intrsPoint)) {
                    intersections.put(intrsPoint, intersection);
                }

                /* Add the intersection to the events list */
                Segment intersectionEvent = new Segment(-1, intrsPoint, intrsPoint);
                events.put(intrsPoint, intersectionEvent);
            }

            if (rightNeighbor != null && doIntersect(s, rightNeighbor)) {
                /* Create and add the intersection to the intersection hash table */
                Point2D intrsPoint = getIntersectionPoint(s, rightNeighbor);
                Intersection intersection = new Intersection(intrsPoint);
                intersection.addSegment(s.getId(),
                        (s.upper().equals(intrsPoint) ? ENDPOINT :
                                s.lower().equals(intrsPoint) ? ENDPOINT : INNERPOINT));
                intersection.addSegment(rightNeighbor.getId(),
                        (rightNeighbor.upper().equals(intrsPoint) ? ENDPOINT :
                                rightNeighbor.lower().equals(intrsPoint) ? ENDPOINT : INNERPOINT));

                if (!intersections.containsKey(intrsPoint)) {
                    intersections.put(intrsPoint, intersection);
                }

                /* Add the intersection to the events list */
                Segment intersectionEvent = new Segment(-1, intrsPoint, intrsPoint);
                events.put(intrsPoint, intersectionEvent);
            }

        } else { //lower end point event
            StdOut.println("Lower end point " + p.toString());
            updateStatus(p.y);
        }
    }

    private Segment getRightNeighbor (Segment s) {
        Segment rightNeighbor = null;
        if (!status.isEmpty()) {
            Double rightKey = status.ceiling(s.upper().x);
            if (rightKey != null) rightNeighbor = status.get(rightKey);
        }
        return rightNeighbor;
    }

    private Segment getLeftNeighbor(Segment s) {
        Segment leftNeighbor = null;
        if (!status.isEmpty()) {
            Double leftKey = status.floor(s.upper().x);
            if (leftKey != null) leftNeighbor = status.get(leftKey);
        }
        return leftNeighbor;
    }

    private boolean doIntersect(Segment a, Segment b) {
        double det1 = a.upper().x * (a.lower().y - b.lower().y) - a.upper().y * (a.lower().x - b.lower().x)
                + a.lower().x * b.lower().y - a.lower().y * b.lower().x;
        double det2 = a.upper().x * (a.lower().y - b.upper().y) - a.upper().y * (a.lower().x - b.upper().x)
                + a.lower().x * b.upper().y - a.lower().y * b.upper().x;
        if (det1 == 0.0 || det2 == 0.0) {
            return true;
        } else if ((det1 > 0.0 && det2 > 0.0) || (det1 < 0.0 && det2 < 0.0)) {
            return false;
        } else {
            det1 = b.upper().x * (b.lower().y - a.lower().y) - b.upper().y * (b.lower().x - a.lower().x)
                    + b.lower().x * a.lower().y - b.lower().y * a.lower().x;
            det2 = b.upper().x * (b.lower().y - a.upper().y) - b.upper().y * (b.lower().x - a.upper().x)
                    + b.lower().x * a.upper().y - b.lower().y * a.upper().x;
            return (!(det1 > 0.0) || !(det2 > 0.0)) && (!(det1 < 0.0) || !(det2 < 0.0));
        }
    }

    private Point2D getIntersectionPoint(Segment a, Segment b) {
        double ma = (a.upper().y - a.lower().y) / (a.upper().x - a.lower().x);
        double mb = (b.upper().y - b.lower().y) / (b.upper().x - b.lower().x);
        double x = (ma * a.upper().x - mb * b.upper().x + b.upper().y - a.upper().y) / (ma - mb);
        double y = (ma * mb * (b.upper().x - a.upper().x) + mb * a.upper().y - ma * b.upper().y) / (mb - ma);
        return new Point2D(x, y);
    }

    //This function updates the current x coordinate of all the segments in the status
    private void updateStatus(double y) {
        if (!status.isEmpty()) {
            for (double x : status.keys()) {
                Segment s = status.get(x);
                double currentX = s.getCurrentX(y);
                status.delete(x);
                status.put(currentX, s);
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Segment s: S) {
            sb.append(String.format("Segment %d: %s\n", i++, s.toString()));
        }
        for (Intersection intersection: intersections.values()) {
            sb.append(intersection.toString());
        }
        return sb.toString();
    }

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        In in = new In("./SegmentsTest.txt");
        SegmentIntersection si = new SegmentIntersection(in);
    }
}
