package kjrg.kmeans.traditionalkmeans.clusterer.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import kjrg.kmeans.traditionalkmeans.clusterer.Clusterer;
import kjrg.kmeans.traditionalkmeans.dataprovider.DataProvider;
import kjrg.kmeans.traditionalkmeans.distance.Distance;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class KMeansClusterer implements Clusterer {

	private DataProvider dataProvider;
	private Distance distance;
	private Random random;
	
	public KMeansClusterer(DataProvider dataProvider, Distance distance) {
		this.dataProvider = dataProvider;
		this.distance = distance;
		random = new Random();
	}
	
	@Override
	public Map<Long, Long> performClustering(List<Point> points, Integer numberOfClusters) {
		// TODO Auto-generated method stub
		return null;
	}

}
