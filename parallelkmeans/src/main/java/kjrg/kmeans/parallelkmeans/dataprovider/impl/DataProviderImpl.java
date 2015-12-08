package kjrg.kmeans.parallelkmeans.dataprovider.impl;

import org.apache.spark.mllib.linalg.Vector;

import kjrg.kmeans.parallelkmeans.dataprovider.DataProvider;

public class DataProviderImpl implements DataProvider {

	private String inputFilepath;
	
	public DataProviderImpl(String inputFilepath) {
		this.inputFilepath = inputFilepath;
	}
	
	@Override
	public Vector getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
