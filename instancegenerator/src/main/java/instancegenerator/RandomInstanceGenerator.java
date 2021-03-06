package instancegenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomInstanceGenerator {

	private static final String SEPARATOR = ";";
	
	private static void saveData(List<Point> points, String output) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(output));
			
			for(Point p : points) {
				writer.write(p.getX() + SEPARATOR + p.getY() + "\n");
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
		int pointsInCluster = Integer.valueOf(args[3]);
		String output = args[4];
		
		List<Point> points = new ArrayList<>();
		
		for(int i = 0; i < numberOfClusters; i++) {
			int middle = start + step * i;
			int range = 1250;
			
			Random rx = new Random();
			Random ry = new Random();
			
			for(int j = 0; j < pointsInCluster; j++) {
				double x = middle + (rx.nextGaussian() * range);
				double y = middle + (ry.nextGaussian() * range);
				points.add(new Point(x, y));
			}
		}
		
		try {
			saveData(points, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Data generated");
	}
}
