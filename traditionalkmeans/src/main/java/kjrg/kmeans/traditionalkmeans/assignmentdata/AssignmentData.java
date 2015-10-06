package kjrg.kmeans.traditionalkmeans.assignmentdata;

import java.util.List;
import java.util.Map;

import kjrg.kmeans.traditionalkmeans.cluster.Cluster;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class AssignmentData {

	private List<Point> points;
	private List<Cluster> clusters;
	private Map<Long, Long> assignment;
	
	public AssignmentData(List<Point> points, List<Cluster> clusters, Map<Long, Long> assignment) {
		this.points = points;
		this.clusters = clusters;
		this.assignment = assignment;
	}

	public List<Point> getPoints() {
		return points;
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public Map<Long, Long> getAssignment() {
		return assignment;
	}
}
