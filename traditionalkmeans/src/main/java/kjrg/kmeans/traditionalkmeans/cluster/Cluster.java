package kjrg.kmeans.traditionalkmeans.cluster;

import java.util.List;

import kjrg.kmeans.traditionalkmeans.point.Point;

public class Cluster {

	private Long id;
	private List<Double> centroidCoordinates;

	public Cluster(Long id, List<Double> coordinates) {
		this.id = id;
		this.centroidCoordinates = coordinates;
	}

	public Long getId() {
		return id;
	}

	public List<Double> getCoordinates() {
		return centroidCoordinates;
	}
	
	public void recalculateCentroid(List<Point> points) {
		
	}
}
