package kjrg.kmeans.traditionalkmeans.cluster;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import kjrg.kmeans.traditionalkmeans.point.Point;

public class ClusterTest {

	@Test
	public void shouldNotRecalculateCentroidForNullArgument() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0, 0.0));
		// when
		cluster.recalculateCentroid(null);
		// then
		assertNotNull(cluster.getCoordinates());
		assertFalse(cluster.getCoordinates().isEmpty());
		assertEquals(0.0, cluster.getCoordinates().get(0), 0.0);
		assertEquals(0.0, cluster.getCoordinates().get(1), 0.0);
	}

	@Test
	public void shouldNotRecalculateCentroidForEmptyArgument() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0, 0.0));
		// when
		cluster.recalculateCentroid(Collections.emptyList());
		// then
		assertNotNull(cluster.getCoordinates());
		assertFalse(cluster.getCoordinates().isEmpty());
		assertEquals(0.0, cluster.getCoordinates().get(0), 0.0);
		assertEquals(0.0, cluster.getCoordinates().get(1), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForPointWithLessDimensions() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0, 0.0));
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.0)));
		// when
		cluster.recalculateCentroid(points);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForPointWithMoreDimensions() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0, 0.0));
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.0, 0.0, 0.0)));
		// when
		cluster.recalculateCentroid(points);
		// then
		fail("No exception was thrown");
	}

	@Test
	public void centroidCoordinatesShouldBeTheSameAfterRecalculating() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0, 0.0));
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.0, 0.0)));
		// when
		cluster.recalculateCentroid(points);
		// then
		assertEquals(0.0, cluster.getCoordinates().get(0), 0.0);
		assertEquals(0.0, cluster.getCoordinates().get(1), 0.0);
	}

	@Test
	public void shouldRecalculateCentroidCoordinatesFor1PointIn1DimensionalSpace() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0));
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(17.0)));
		// when
		cluster.recalculateCentroid(points);
		// then
		assertEquals(17.0, cluster.getCoordinates().get(0), 0.0);
	}

	@Test
	public void shouldRecalculateCentroidCoordinatesFor2PointsIn1DimensionalSpace() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0));
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.0)), new Point(2L, Arrays.asList(10.0)));
		// when
		cluster.recalculateCentroid(points);
		// then
		assertEquals(5.0, cluster.getCoordinates().get(0), 0.0);
	}

	@Test
	public void shouldRecalculateCentroidCoordinatesFor1PointIn2DimensionalSpace() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0, 0.0));
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(1.0, 2.0)));
		// when
		cluster.recalculateCentroid(points);
		// then
		assertEquals(1.0, cluster.getCoordinates().get(0), 0.0);
		assertEquals(2.0, cluster.getCoordinates().get(1), 0.0);
	}

	@Test
	public void shouldRecalculateCentroidCoordinatesFor2PointsIn2DimensionalSpace() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0, 0.0));
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.5, 2.7)),
				new Point(2L, Arrays.asList(19.5, 1.3)));
		// when
		cluster.recalculateCentroid(points);
		// then
		assertEquals(10.0, cluster.getCoordinates().get(0), 0.0);
		assertEquals(2.0, cluster.getCoordinates().get(1), 0.0);
	}

	@Test
	public void shouldRecalculateCentroidCoordinatesFor4PointsIn3DimensionalSpace() {
		// given
		Cluster cluster = new Cluster(1L, Arrays.asList(15.8, 2.1, 7.4));
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.5, 7.9, 3.1)),
				new Point(2L, Arrays.asList(3.8, 2.8, 5.0)), new Point(3L, Arrays.asList(0.0, 0.0, 0.0)),
				new Point(4L, Arrays.asList(4.7, 2.1, 11.9)));
		// when
		cluster.recalculateCentroid(points);
		// then
		assertEquals(2.25, cluster.getCoordinates().get(0), 0.00001);
		assertEquals(3.2, cluster.getCoordinates().get(1), 0.00001);
		assertEquals(5.0, cluster.getCoordinates().get(2), 0.00001);
	}

}
