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

public class ParallelKMeansClusterer implements Clusterer {

	private Distance distance;
	private Random random;
	
	public ParallelKMeansClusterer(Distance distance) {
		this.distance = distance;
		random = new Random();
	}
	
	@Override
	public AssignmentData performClustering(List<Point> points, Integer numberOfClusters) {
		if(points == null || numberOfClusters == null) {
			throw new NullPointerException("Null pointer was passed to method performClustering.");
		}
		if(numberOfClusters > points.size() || numberOfClusters < 1) {
			throw new IllegalArgumentException("Wrong number of clusters.");
		}
		
		Map<Cluster, List<Point>> oldAssignment = Collections.emptyMap();
		Map<Cluster, List<Point>> currentAssignment = Collections.emptyMap();
		
		/*
		 * Step 1 - initialize clusters with random points.
		 */
		List<Cluster> clusters = initRandomClusters(points, numberOfClusters);
		
		do {
			oldAssignment = new HashMap<>(currentAssignment);
		
			System.out.println("Iteration");
			
			/*
			 * Step 2 - assign each point to cluster with nearest centroid.
			 */
			currentAssignment = assignPointsToClusters(points, clusters);
			Map<Cluster, List<Point>> duplicate = currentAssignment;
			
			/*
			 * Step 3 - recalculate clusters centroids.
			 */
			clusters.parallelStream().forEach(c -> {
//				List<Point> assignedPoints = points.stream().filter(p -> c.getId() == p.getAssignedClusterId()).collect(Collectors.toList());
//				c.recalculateCentroid(assignedPoints);
				c.recalculateCentroid(duplicate.get(c));
			});
		} while(!currentAssignment.equals(oldAssignment));
		
		return new AssignmentData(points, clusters, currentAssignment);
//		return null;
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
	
	private Map<Cluster, List<Point>> assignPointsToClusters(List<Point> points, List<Cluster> clusters) {
		Map<Cluster, List<Point>> assignment = new HashMap<>();
		
		for(Cluster c: clusters) {
			assignment.put(c, new ArrayList<>());
		}
		
		/*
		points.parallelStream().forEach(point -> {
			Double minDistance = Double.MAX_VALUE;
//			Long nearestClusterId = 0L;
			Cluster nearestCluster = null;
			for(Cluster c : clusters) {
				Double distanceBetween = distance.distanceBetween(point, c);
				if(distanceBetween < minDistance) {
					minDistance = distanceBetween;
//					nearestClusterId = c.getId();
					nearestCluster = c;
				}
			}
			point.setAssignedClusterId(nearestCluster.getId());
//			assignment.put(p.getId(), nearestClusterId);
			synchronized (assignment) {				
				assignment.get(nearestCluster).add(point);
//				assignment.put(point.getId(), point.getAssignedClusterId());
			}
		});
		*/
		
		points.parallelStream().forEach(p -> {
			Double minDistance = Double.MAX_VALUE;
//			Long nearestClusterId = 0L;
			Cluster nearestCluster = null;
			for(Cluster c : clusters) {
				Double distanceBetween = distance.distanceBetween(p, c);
				if(distanceBetween < minDistance) {
					minDistance = distanceBetween;
//					nearestClusterId = c.getId();
					nearestCluster = c;
				}
			}
			p.setAssignedClusterId(nearestCluster.getId());
//			assignment.put(p.getId(), nearestClusterId);
			synchronized (assignment) {
				assignment.get(nearestCluster).add(p);
			}
		});
		
		return assignment;
	}
}
