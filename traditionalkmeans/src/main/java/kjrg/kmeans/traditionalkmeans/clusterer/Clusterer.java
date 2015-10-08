package kjrg.kmeans.traditionalkmeans.clusterer;

import java.util.List;

import kjrg.kmeans.traditionalkmeans.assignmentdata.AssignmentData;
import kjrg.kmeans.traditionalkmeans.point.Point;

public interface Clusterer {

	/*
	 * Clustering is a process of assigning each point to a cluster.
	 */
	AssignmentData performClustering(List<Point> points, Integer numberOfClusters);
}
