package kjrg.kmeans.traditionalkmeans.distance.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import kjrg.kmeans.traditionalkmeans.cluster.Cluster;
import kjrg.kmeans.traditionalkmeans.distance.Distance;
import kjrg.kmeans.traditionalkmeans.point.Point;

public class EuclideanDistanceTest {

	private Distance distance;
	
	@Before
	public void setUp() {
		distance = new EuclideanDistance();
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionForNullPointArgument() {
		// given
		Cluster cluster = new Cluster(1L, Collections.emptyList());
		// when
		distance.distanceBetween(null, cluster);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForEmptyPointCoordinates() {
		// given
		Point point = new Point(1L, Collections.emptyList());
		Cluster cluster = new Cluster(1L, Collections.emptyList());
		// when
		distance.distanceBetween(point, cluster);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionForNullClusterArgument() {
		// given
		Point point = new Point(1L, Arrays.asList(1.0, 1.0));
		// when
		distance.distanceBetween(point, null);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForEmptyClusterCentroid() {
		// given
		Point point = new Point(1L, Arrays.asList(1.0, 1.0));
		Cluster cluster = new Cluster(1L, Collections.emptyList());
		// when
		distance.distanceBetween(point, cluster);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForPointWithMoreDimensionsThenCentroid() {
		// given
		Point point = new Point(1L, Arrays.asList(1.0, 1.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(1.0, 1.0, 1.0));
		// when
		distance.distanceBetween(point, cluster);
		// then
		fail("No exception was thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForCentroidWithMoreDimensionsPoint() {
		// given
		Point point = new Point(1L, Arrays.asList(1.0, 1.0, 1.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(1.0, 1.0));
		// when
		distance.distanceBetween(point, cluster);
		// then
		fail("No exception was thrown");
	}

	@Test
	public void shouldReturn0ForEqualCoordinatesIn1DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(0.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(0.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(0.0, distanceBetween, 0.0);
	}

	@Test
	public void shouldReturn0ForEqualCoordinatesIn2DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(1.0, 1.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(1.0, 1.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(0.0, distanceBetween, 0.0);
	}

	@Test
	public void shouldReturn1ForCoordinatesIn1DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(0.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(1.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(1.0, distanceBetween, 0.0);
	}

	@Test
	public void shouldReturn1ForCoordinatesIn2DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(0.0, 1.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(1.0, 1.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(1.0, distanceBetween, 0.0);
	}

	@Test
	public void shouldReturn2ForCoordinatesIn2DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(1.0, 1.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(1.0, 3.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(2.0, distanceBetween, 0.0);
	}

	@Test
	public void shouldReturnSquareRootOf2ForCoordinatesIn2DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(1.0, 1.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(2.0, 2.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(1.41421, distanceBetween, 0.00001);
	}
	
	@Test
	public void shouldReturn3ForCoordinatesIn3DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(1.0, 1.0, 1.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(1.0, 1.0, 4.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(3.0, distanceBetween, 0.0);
	}

	@Test
	public void shouldReturnSquareRootOf3ForCoordinatesIn3DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(7.0, 7.0, 7.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(8.0, 8.0, 8.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(1.73205, distanceBetween, 0.00001);
	}

	@Test
	public void shouldReturn17ForCoordinatesIn4DimensionalSpace() {
		// given
		Point point = new Point(1L, Arrays.asList(7.0, 7.0, 7.0, 0.0));
		Cluster cluster = new Cluster(1L, Arrays.asList(7.0, 7.0, 7.0, 17.0));
		// when
		Double distanceBetween = distance.distanceBetween(point, cluster);
		// then
		assertEquals(17.0, distanceBetween, 0.0);
	}

}
