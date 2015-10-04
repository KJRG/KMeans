package kjrg.kmeans.traditionalkmeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kjrg.kmeans.traditionalkmeans.clusterer.Clusterer;
import kjrg.kmeans.traditionalkmeans.clusterer.impl.KMeansClusterer;
import kjrg.kmeans.traditionalkmeans.dataprovider.DataProvider;
import kjrg.kmeans.traditionalkmeans.dataprovider.impl.DataProviderImpl;
import kjrg.kmeans.traditionalkmeans.distance.impl.EuclideanDistance;
import kjrg.kmeans.traditionalkmeans.exception.BadDataException;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class KMeansClustering  {
	
    public static void main(String[] args) {
    	DataProvider dataProvider = new DataProviderImpl(args[0], args[1]);
    	List<Point> points = new ArrayList<>();
    	try {
			points = dataProvider.getData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Clusterer clusterer = new KMeansClusterer(new EuclideanDistance());
    	Map<Long, Long> assignment = clusterer.performClustering(points, 2);
    	try {
			dataProvider.saveData(assignment);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
