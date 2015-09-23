package kjrg.kmeans.traditionalkmeans.clusterer;

import java.util.List;
import java.util.Map;

import kjrg.kmeans.traditionalkmeans.point.Point;

public interface Clusterer {

	/*
	 * Clustering is a process of assigning each point to a cluster.
	 */
	Map<Long, Long> performClustering(List<Point> points, Integer numberOfClusters);
}
