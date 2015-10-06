package kjrg.kmeans.traditionalkmeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kjrg.kmeans.traditionalkmeans.assignmentdata.AssignmentData;
import kjrg.kmeans.traditionalkmeans.clusterer.Clusterer;
import kjrg.kmeans.traditionalkmeans.clusterer.impl.KMeansClusterer;
import kjrg.kmeans.traditionalkmeans.dataprovider.DataProvider;
import kjrg.kmeans.traditionalkmeans.dataprovider.impl.DataProviderImpl;
import kjrg.kmeans.traditionalkmeans.distance.impl.EuclideanDistance;
import kjrg.kmeans.traditionalkmeans.exception.BadDataException;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class KMeansClustering {

	public static void main(String[] args) {
		if(args.length < 3) {
			System.out.println("Not enough arguments");
			System.exit(1);
		}
		
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
		System.out.println("Starting clustering");
		Long startTime = System.nanoTime();
		AssignmentData assignmentData = clusterer.performClustering(points, Integer.valueOf(args[2]));
		Long stopTime = System.nanoTime();
		System.out.println("Clustering finished, computations time: " + (stopTime - startTime) / 1000000 + "[ms]");
		

		try {
			dataProvider.saveData(assignmentData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
