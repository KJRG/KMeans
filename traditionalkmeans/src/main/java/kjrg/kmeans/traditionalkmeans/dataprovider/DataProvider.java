package kjrg.kmeans.traditionalkmeans.dataprovider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import kjrg.kmeans.traditionalkmeans.assignmentdata.AssignmentData;
import kjrg.kmeans.traditionalkmeans.exception.BadDataException;
import kjrg.kmeans.traditionalkmeans.point.Point;

public interface DataProvider {

	List<Point> getData() throws IOException, FileNotFoundException, BadDataException;
	void saveData(AssignmentData assignmentData) throws IOException;
}
