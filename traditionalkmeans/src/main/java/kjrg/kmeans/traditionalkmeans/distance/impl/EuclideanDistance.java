package kjrg.kmeans.traditionalkmeans.distance.impl;

import kjrg.kmeans.traditionalkmeans.cluster.Cluster;
import kjrg.kmeans.traditionalkmeans.distance.Distance;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class EuclideanDistance implements Distance {

	public EuclideanDistance() {
	}

	@Override
	public Double distanceBetween(Point point, Cluster clusterCentroid) {
		if (point == null || clusterCentroid == null) {
			throw new NullPointerException("Null argument passed to method distanceBetween");
		}

		if (point.getCoordinates().isEmpty() || clusterCentroid.getCoordinates().isEmpty()) {
			throw new IllegalArgumentException(
					"Empty coordinates of point or cluster centroid in method distanceBetween");
		}

		if (point.getCoordinates().size() != clusterCentroid.getCoordinates().size()) {
			throw new IllegalArgumentException(
					"Number of coordinates of point does not match number of coordinates of cluster centroid.");
		}

		Double distance = 0.0;
		
		for(int i = 0; i < point.getCoordinates().size(); i++) {
			distance += Math.pow(point.getCoordinates().get(i) - clusterCentroid.getCoordinates().get(i), 2.0);
		}

		return Math.sqrt(distance);
	}

}
