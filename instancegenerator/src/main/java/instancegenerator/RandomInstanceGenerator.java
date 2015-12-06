package instancegenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomInstanceGenerator {

//	private static final int POINTS_IN_CLUSTER = 100000;
	private static final int POINTS_IN_CLUSTER = 20000;
	private static final String OUTPUT = "C:\\KMeans\\random-data.csv";
	private static final String SEPARATOR = ",";
	
	private static void saveData(List<Point> points) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(OUTPUT));
			
			for(Point p : points) {
				writer.write(p.getX() + SEPARATOR + p.getY() + SEPARATOR + "x\n");
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch(IOException e) {
					throw e;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int numberOfClusters = Integer.valueOf(args[0]);
		int start = Integer.valueOf(args[1]);
		int step = Integer.valueOf(args[2]);
		List<Point> points = new ArrayList<>();
		
		for(int i = 0; i < numberOfClusters; i++) {
			int middle = start + step * i;
			int range = middle / 2;
			
			System.out.println(middle + " " + range);
			
			Random rx = new Random();
			Random ry = new Random();
			
			for(int j = 0; j < POINTS_IN_CLUSTER; j++) {
				double x = middle + (rx.nextGaussian() * range);
				double y = middle + (ry.nextGaussian() * range);
				points.add(new Point(x, y));
			}
		}
		
		try {
			saveData(points);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Data generated");
	}
}
