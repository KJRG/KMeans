package kjrg.kmeans.parallelkmeans;

import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.SparkConf;

public class ParallelKMeansClustering  {
	
	private static final String SEPARATOR = ";";
	private static final int NUM_OF_ITERATIONS = 20;
	
    public static void main(String[] args) {
    	if(args.length < 3) {
    		System.out.println("Podano zbyt mało argumentów");
    		System.exit(1);
    	}
    	
    	SparkConf conf = new SparkConf().setAppName("Parallel k-means");
    	JavaSparkContext sc = new JavaSparkContext(conf);
    	
    	String inputFilepath = args[0];
    	int numberOfMeasurements = Integer.parseInt(args[1]);
    	int numberOfClusters = Integer.parseInt(args[2]);
    	
    	JavaRDD<String> data = sc.textFile(inputFilepath);
    	JavaRDD<Vector> parsedData = data.map(
    			new Function<String, Vector>() {
    				public Vector call(String featuresRow) {
    					String[] features = featuresRow.split(SEPARATOR);
    					double[] featuresValues = new double[features.length];
    					
    					for(int i = 0; i < features.length; i++) {
    						featuresValues[i] = Double.parseDouble(features[i]);
    					}
    					
    					return Vectors.dense(featuresValues);
    				}
    			}
    	);
    	parsedData.cache();

    	long totalTime = 0;
    	
    	for(int i = 1; i <= numberOfMeasurements; i++) {
    		System.out.println("Równoległy algorytm k -średnich - pomiar " + i);
    		Long startTime = System.nanoTime();
    		KMeansModel clusters = KMeans.train(parsedData.rdd(), numberOfClusters, NUM_OF_ITERATIONS);
			Long stopTime = System.nanoTime();
			totalTime += (stopTime - startTime) / 1000000;
    	}

    	System.out.println("Sredni czas pomiaru dla instancji " + inputFilepath + ": " + totalTime + " [ms]");
    }
}
