package kjrg.kmeans.traditionalkmeans.dataprovider.impl;

import java.util.List;
import java.util.Map;

import kjrg.kmeans.traditionalkmeans.dataprovider.DataProvider;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class DataProviderImpl implements DataProvider {

	private String inputFilepath;
	
	public DataProviderImpl(String inputFilepath) {
		this.inputFilepath = inputFilepath;
	}
	
	@Override
	public List<Point> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Point> readDataFromFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveData(Map<Long, Long> assignment, String outputFilepath) {
		// TODO Auto-generated method stub
	}
}
