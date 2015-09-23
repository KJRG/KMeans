package kjrg.kmeans.traditionalkmeans.point;

import java.util.List;

public class Point {

	private Long id;
	private List<Double> coordinates;

	public Point(Long id, List<Double> coordinates) {
		this.id = id;
		this.coordinates = coordinates;
	}

	public Long getId() {
		return id;
	}

	public List<Double> getCoordinates() {
		return coordinates;
	}
}
