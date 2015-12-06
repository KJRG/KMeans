package kjrg.kmeans.traditionalkmeans.assignmentdata;

import java.util.List;
import java.util.Map;

import kjrg.kmeans.traditionalkmeans.cluster.Cluster;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class AssignmentData {

	private List<Point> points;
	private List<Cluster> clusters;
	private Map<Cluster, List<Point>> assignment;
	
	public AssignmentData(List<Point> points, List<Cluster> clusters, Map<Cluster, List<Point>> currentAssignment) {
		this.points = points;
		this.clusters = clusters;
		this.assignment = currentAssignment;
	}

	public List<Point> getPoints() {
		return points;
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public Map<Cluster, List<Point>> getAssignment() {
		return assignment;
	}
}
