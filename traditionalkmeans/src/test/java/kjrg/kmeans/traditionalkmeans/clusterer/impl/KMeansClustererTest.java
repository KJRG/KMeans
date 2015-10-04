package kjrg.kmeans.traditionalkmeans.clusterer.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kjrg.kmeans.traditionalkmeans.distance.impl.EuclideanDistance;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class KMeansClustererTest {

	private KMeansClusterer kMeansClusterer;

	@Before
	public void setUp() {
		kMeansClusterer = new KMeansClusterer(new EuclideanDistance());
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionForNullPointsArgument() {
		// given when
		kMeansClusterer.performClustering(null, 3);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionForNullNumberOfClustersArgument() {
		// given
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.0)));
		// when
		kMeansClusterer.performClustering(points, null);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForNumberOfClustersLessThen0() {
		// given
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.0)));
		// when
		kMeansClusterer.performClustering(points, -1);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForNumberOfClustersEqual0() {
		// given
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.0)));
		// when
		kMeansClusterer.performClustering(points, 0);
		// then
		fail("No exception was thrown");
	}

	@Test
	public void shouldPerformClustering() {
		// given
		List<Point> points = Arrays.asList(new Point(1L, Arrays.asList(0.0, 0.0)),
				new Point(2L, Arrays.asList(1.0, 1.0)), new Point(3L, Arrays.asList(2.0, 3.0)));
		// when
		Map<Long, Long> assignment = kMeansClusterer.performClustering(points, 3);
		// then
		assertNotNull(assignment);
		assertEquals(3, assignment.size());
	}
}
