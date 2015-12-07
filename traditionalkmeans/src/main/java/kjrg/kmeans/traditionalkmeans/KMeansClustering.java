package kjrg.kmeans.traditionalkmeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ForkJoinPool;

import kjrg.kmeans.traditionalkmeans.clusterer.Clusterer;
import kjrg.kmeans.traditionalkmeans.clusterer.impl.KMeansClusterer;
import kjrg.kmeans.traditionalkmeans.clusterer.impl.ParallelKMeansClusterer;
import kjrg.kmeans.traditionalkmeans.dataprovider.DataProvider;
import kjrg.kmeans.traditionalkmeans.dataprovider.impl.DataProviderImpl;
import kjrg.kmeans.traditionalkmeans.distance.impl.EuclideanDistance;
import kjrg.kmeans.traditionalkmeans.exception.BadDataException;
import kjrg.kmeans.traditionalkmeans.point.Point;


public class KMeansClustering {

	private static final String PROPERTIES_FILENAME = "kjrg/kmeans/traditionalkmeans/config.properties";
	private static final String SEP = System.getProperty("line.separator");
	private static final String PARALLELISM_LVL_SYS_PROPERTY = "java.util.concurrent.ForkJoinPool.common.parallelism";
	private static final String DEF_PARALLELISM_LVL = Integer.toString(Runtime.getRuntime().availableProcessors() - 1);
	
	private static long regularKMeansMeasurements(int numberOfMeasurements, int numberOfClusters, List<Point> points) {
		Clusterer clusterer = new KMeansClusterer(new EuclideanDistance());
		long totalTime = 0;
		
		for(int i = 1; i <= numberOfMeasurements; i++) {
			System.out.println("Algorytm szeregowy - pomiar " + i);
			Long startTime = System.nanoTime();
			clusterer.performClustering(points, numberOfClusters);
			Long stopTime = System.nanoTime();
			totalTime += (stopTime - startTime) / 1000000;
		}
		
		return totalTime / numberOfMeasurements;
	}
	
	private static long multithreadedKMeansMeasurements(int numberOfMeasurements, int numberOfClusters, List<Point> points, Integer parallelismLevel) {
		Clusterer clusterer = new ParallelKMeansClusterer(new EuclideanDistance());
		long totalTime = 0;
		
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty(PARALLELISM_LVL_SYS_PROPERTY, parallelismLevel.toString());
		
		System.out.println("Parallelism: " + ForkJoinPool.getCommonPoolParallelism());
		
		for(int i = 1; i <= numberOfMeasurements; i++) {
			System.out.println("Algorytm wielowątkowy - pomiar " + i);
			Long startTime = System.nanoTime();
			clusterer.performClustering(points, numberOfClusters);
			Long stopTime = System.nanoTime();
			totalTime += (stopTime - startTime) / 1000000;
		}
		
		systemProperties.setProperty(PARALLELISM_LVL_SYS_PROPERTY, DEF_PARALLELISM_LVL);
		return totalTime / numberOfMeasurements;
	}
	
	private static void saveReport(String report, String filepath) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filepath);
			writer.write(report);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
	
	public static void main(String[] args) {
		InputStream inputStream = null;
		String output = "";
		String input = "";
		int numberOfMeasurements = 0;
		int numberOfClusters = 0;
		int maxNumberOfThreads = 0;
		boolean regular = false;
		boolean multithreading = false;
		
		try {
			Properties properties = new Properties();
			inputStream = KMeansClustering.class.getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
			
			if(inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("File " + PROPERTIES_FILENAME + " not found");
			}
			
			output = properties.getProperty("output");
			input = properties.getProperty("input");
			numberOfMeasurements = Integer.parseInt(properties.getProperty("numberOfMeasurements"));
			numberOfClusters = Integer.parseInt(properties.getProperty("numberOfClusters"));
			maxNumberOfThreads = Integer.parseInt(properties.getProperty("maxThreads"));
			regular = Boolean.parseBoolean(properties.getProperty("regular"));
			multithreading = Boolean.parseBoolean(properties.getProperty("multithreading"));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		StringBuilder reportBuilder = new StringBuilder();
		reportBuilder.append("Raport:" + SEP + "Ilość pomiarów: " + numberOfMeasurements + SEP + "Ilość klastrów: " + numberOfClusters + SEP);
		
		String[] inputFilepaths = input.split(";");
		for(String filepath : inputFilepaths) {
			DataProvider dataProvider = new DataProviderImpl(filepath, null);
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
			
			reportBuilder.append("Dane wejściowe: " + filepath + SEP);

			// TODO JVM Warmup
			
			if(regular) {
				long time = regularKMeansMeasurements(numberOfMeasurements, numberOfClusters, points);
				reportBuilder.append("Średni czas dla szeregowego algorytmu k-średnich: " + time + " [ms]" + SEP);
			}

			if(multithreading) {
				long time = multithreadedKMeansMeasurements(numberOfMeasurements, numberOfClusters, points, maxNumberOfThreads);
				reportBuilder.append("Średni czas dla wielowątkowego algorytmu k-średnich: " + time + " [ms]" + SEP);
			}
		}
		
		System.out.println(reportBuilder.toString());
		saveReport(reportBuilder.toString(), output);
	}
}
