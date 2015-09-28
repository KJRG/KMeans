package kjrg.kmeans.traditionalkmeans.point;

import java.util.List;

public class Point {

	private Long id;
	private List<Double> coordinates;
	private Long assignedClusterId;
	
	public Point(Long id, List<Double> coordinates) {
		this.id = id;
		this.coordinates = coordinates;
		this.assignedClusterId = null;
	}

	public Long getId() {
		return id;
	}

	public List<Double> getCoordinates() {
		return coordinates;
	}
	
	public Long getAssignedClusterId() {
		return assignedClusterId;
	}

	public void setAssignedClusterId(Long assignedClusterId) {
		this.assignedClusterId = assignedClusterId;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", coordinates=" + coordinates + "]";
	}
}
