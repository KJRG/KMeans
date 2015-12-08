package kjrg.kmeans.parallelkmeans.dataprovider;

import org.apache.spark.mllib.linalg.Vector;

public interface DataProvider {

	public Vector getData();
}
