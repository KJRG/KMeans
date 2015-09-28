package kjrg.kmeans.traditionalkmeans.dataprovider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import kjrg.kmeans.traditionalkmeans.exception.BadDataException;
import kjrg.kmeans.traditionalkmeans.point.Point;

public interface DataProvider {

	List<Point> getData() throws IOException, FileNotFoundException, BadDataException;
	void saveData(Map<Long, Long> assignment);
}
