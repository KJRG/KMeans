package kjrg.kmeans.traditionalkmeans.distance;

import kjrg.kmeans.traditionalkmeans.cluster.Cluster;
import kjrg.kmeans.traditionalkmeans.point.Point;

public interface Distance {

	Double distanceBetween(Point point, Cluster clusterCentroid);
}
