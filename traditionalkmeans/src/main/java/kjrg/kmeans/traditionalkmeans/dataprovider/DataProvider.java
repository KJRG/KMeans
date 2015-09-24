package kjrg.kmeans.traditionalkmeans.dataprovider;

import java.util.List;
import java.util.Map;

import kjrg.kmeans.traditionalkmeans.point.Point;

public interface DataProvider {

	List<Point> getData();
	void saveData(Map<Long, Long> assignment);
}
