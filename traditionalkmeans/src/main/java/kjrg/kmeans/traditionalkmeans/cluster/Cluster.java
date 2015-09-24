package kjrg.kmeans.traditionalkmeans.cluster;

import java.util.ArrayList;
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
		if (points == null || points.isEmpty()) {
			return;
		}

		for (Point point : points) {
			if (point.getCoordinates().size() != centroidCoordinates.size()) {
				throw new IllegalArgumentException(
						"Wrong number of dimensions for one of given points in method recalculateCentroid");
			}
		}

		List<Double> calculatedCentroidCoordinates = new ArrayList<>();

		/*
		 * For each coordinate (i)...
		 */
		for (int i = 0; i < centroidCoordinates.size(); i++) {
			Double coordinate = 0.0;
			
			/*
			 * ... get the coordinate (i) from each point (j).
			 */
			for(int j = 0; j < points.size(); j++) {
				coordinate += points.get(j).getCoordinates().get(i);
			}
			
			calculatedCentroidCoordinates.add(coordinate / ((double) points.size()));
		}

		centroidCoordinates = calculatedCentroidCoordinates;
	}
}
