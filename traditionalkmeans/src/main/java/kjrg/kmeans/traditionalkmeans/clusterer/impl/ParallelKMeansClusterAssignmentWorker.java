package kjrg.kmeans.traditionalkmeans.clusterer.impl;

import java.util.List;

import kjrg.kmeans.traditionalkmeans.cluster.Cluster;
import kjrg.kmeans.traditionalkmeans.distance.Distance;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class ParallelKMeansClusterAssignmentWorker implements Runnable {

	private Point point;
	private List<Cluster> clusters;
	private Distance distance;
	
	public ParallelKMeansClusterAssignmentWorker(Point point, List<Cluster> clusters, Distance distance) {
		this.point = point;
		this.clusters = clusters;
		this.distance = distance;
	}

	@Override
	public void run() {
		Double minDistance = Double.MAX_VALUE;
		Long nearestClusterId = 0L;
		for(Cluster c : clusters) {
			Double distanceBetween = distance.distanceBetween(point, c);
			if(distanceBetween < minDistance) {
				minDistance = distanceBetween;
				nearestClusterId = c.getId();
			}
		}
		point.setAssignedClusterId(nearestClusterId);
	}

}
