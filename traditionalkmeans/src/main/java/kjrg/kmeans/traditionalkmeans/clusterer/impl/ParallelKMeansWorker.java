package kjrg.kmeans.traditionalkmeans.clusterer.impl;

import java.util.List;

import kjrg.kmeans.traditionalkmeans.cluster.Cluster;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class ParallelKMeansWorker implements Runnable {

	private Cluster cluster;
	private List<Point> points;
	
	public ParallelKMeansWorker(Cluster cluster, List<Point> points) {
		this.cluster = cluster;
		this.points = points;
	}

	@Override
	public void run() {
		cluster.recalculateCentroid(points);
	}

}
