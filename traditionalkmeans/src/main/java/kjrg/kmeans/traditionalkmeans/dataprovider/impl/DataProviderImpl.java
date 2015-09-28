package kjrg.kmeans.traditionalkmeans.dataprovider.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kjrg.kmeans.traditionalkmeans.dataprovider.DataProvider;
import kjrg.kmeans.traditionalkmeans.exception.BadDataException;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class DataProviderImpl implements DataProvider {

	private static final String SEPARATOR = ",";
	
	private String inputFilepath;
	private String outputFilepath;
	
	public DataProviderImpl(String inputFilepath, String outputFilepath) {
		this.inputFilepath = inputFilepath;
		this.outputFilepath = outputFilepath;
	}
	
	@Override
	public List<Point> getData() throws IOException, FileNotFoundException, BadDataException {
		List<Point> data = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(inputFilepath));
			Integer numberOfFeatures = 0;
			String featuresRow = null;
			Long id = 1L;
			
			while((featuresRow = reader.readLine()) != null) {
				String[] features = featuresRow.split(SEPARATOR);

				if(numberOfFeatures != 0 && features.length != numberOfFeatures) {
					throw new BadDataException("Bad number of features in row " + id);
				}
				numberOfFeatures = features.length;
				
				List<Double> featuresValues = new ArrayList<>();
				for(int i = 0; i < numberOfFeatures - 1; i++) {
					featuresValues.add(Double.parseDouble(features[i]));
				}
				
				data.add(new Point(id, featuresValues));
				id++;
			}
		} catch (FileNotFoundException e) {
			throw e;
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		
		return data;
	}

	@Override
	public void saveData(Map<Long, Long> assignment) {
		// TODO Auto-generated method stub
	}
}
