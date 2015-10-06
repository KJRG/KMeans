package kjrg.kmeans.traditionalkmeans.clusterer.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import kjrg.kmeans.traditionalkmeans.assignmentdata.AssignmentData;
import kjrg.kmeans.traditionalkmeans.cluster.Cluster;
import kjrg.kmeans.traditionalkmeans.clusterer.Clusterer;
import kjrg.kmeans.traditionalkmeans.distance.Distance;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class KMeansClusterer implements Clusterer {

	private Distance distance;
	private Random random;
	private List<Cluster> clusters;
	
	public KMeansClusterer(Distance distance) {
		this.distance = distance;
		random = new Random();
		clusters = Collections.emptyList();
	}
	
	@Override
	public AssignmentData performClustering(List<Point> points, Integer numberOfClusters) {
		if(points == null || numberOfClusters == null) {
			throw new NullPointerException("Null pointer was passed to method performClustering.");
		}
		if(numberOfClusters > points.size() || numberOfClusters < 1) {
			throw new IllegalArgumentException("Wrong number of clusters.");
		}
		
		Map<Long, Long> oldAssignment = Collections.emptyMap();
		Map<Long, Long> currentAssignment = Collections.emptyMap();
		
		/*
		 * Step 1 - initialize clusters with random points.
		 */
		List<Cluster> clusters = initRandomClusters(points, numberOfClusters);
		
		do {
			oldAssignment = new HashMap<>(currentAssignment);
			
			/*
			 * Step 2 - assign each point to cluster with nearest centroid.
			 */
			currentAssignment = assignPointsToClusters(points, clusters);
			
			/*
			 * Step 3 - recalculate clusters centroids.
			 */
			for(Cluster c : clusters) {
				List<Point> assignedPoints = points.stream().filter(p -> c.getId() == p.getAssignedClusterId()).collect(Collectors.toList());
				c.recalculateCentroid(assignedPoints);
			}
		} while(!currentAssignment.equals(oldAssignment));
		
		this.clusters = clusters;
		return new AssignmentData(points, clusters, currentAssignment);
	}
	
	public List<Cluster> getClusters() {
		return clusters;
	}

	private List<Cluster> initRandomClusters(List<Point> points, Integer numberOfClusters) {
		if(points == null || numberOfClusters == null) {
			throw new NullPointerException();
		}

		List<Cluster> clusters = new ArrayList<>();
		Set<Long> availableIds = points.stream().map(Point::getId).collect(Collectors.toSet());
		for(int i = 1; i <= numberOfClusters; i++) {
			Integer randomId = random.nextInt(availableIds.size()) + 1;
			availableIds.remove(randomId);
			clusters.add(new Cluster((long) i, points.get(randomId - 1).getCoordinates()));
		}
		
		return clusters;
	}
	
	private Map<Long, Long> assignPointsToClusters(List<Point> points, List<Cluster> clusters) {
		Map<Long, Long> assignment = new HashMap<>();
		
		for(Point p : points) {
			Double minDistance = Double.MAX_VALUE;
			Long nearestClusterId = 0L;
			for(Cluster c : clusters) {
				Double distanceBetween = distance.distanceBetween(p, c);
				if(distanceBetween < minDistance) {
					minDistance = distanceBetween;
					nearestClusterId = c.getId();
				}
			}
			p.setAssignedClusterId(nearestClusterId);
			assignment.put(p.getId(), nearestClusterId);
		}
		
		return assignment;
	}
}
