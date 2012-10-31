package net.imglib2.ops.pointset;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.imglib2.IterableInterval;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.type.numeric.real.DoubleType;

import org.junit.Test;

public class IterableIntervalPointSetIteratorTest {

	@Test
	public void test() {
		ArrayImgFactory<DoubleType> factory = new ArrayImgFactory<DoubleType>();
		Img<DoubleType> img = factory.create(new long[] { 4 }, new DoubleType());
		IterableInterval<DoubleType> interval = img;
		PointSet ps = new IterableIntervalPointSet(interval);
		PointSetIterator iter = ps.iterator();

		// regular sequence
		assertTrue(iter.hasNext());
		assertArrayEquals(new long[] { 0 }, iter.next());
		assertTrue(iter.hasNext());
		assertArrayEquals(new long[] { 1 }, iter.next());
		assertTrue(iter.hasNext());
		assertArrayEquals(new long[] { 2 }, iter.next());
		assertTrue(iter.hasNext());
		assertArrayEquals(new long[] { 3 }, iter.next());
		assertFalse(iter.hasNext());

		// another regular sequence
		iter.reset();
		assertTrue(iter.hasNext());
		iter.fwd();
		assertArrayEquals(new long[] { 0 }, iter.get());
		assertTrue(iter.hasNext());
		iter.fwd();
		assertArrayEquals(new long[] { 1 }, iter.get());
		assertTrue(iter.hasNext());
		iter.fwd();
		assertArrayEquals(new long[] { 2 }, iter.get());
		assertTrue(iter.hasNext());
		iter.fwd();
		assertArrayEquals(new long[] { 3 }, iter.get());
		assertFalse(iter.hasNext());

		// irregular sequences
		iter.reset();
		iter.next();
		assertArrayEquals(new long[] { 0 }, iter.get());
		iter.fwd();
		assertArrayEquals(new long[] { 1 }, iter.get());
		iter.next();
		assertArrayEquals(new long[] { 2 }, iter.get());
		iter.fwd();
		assertArrayEquals(new long[] { 3 }, iter.get());
		assertFalse(iter.hasNext());
		assertArrayEquals(new long[] { 3 }, iter.get());

	}
}