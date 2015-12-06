package kjrg.kmeans.traditionalkmeans.clusterer;

import java.util.List;

import kjrg.kmeans.traditionalkmeans.assignmentdata.AssignmentData;
import kjrg.kmeans.traditionalkmeans.point.Point;

public interface Clusterer {

	AssignmentData performClustering(List<Point> points, Integer numberOfClusters);
}
