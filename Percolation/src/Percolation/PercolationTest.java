package Percolation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PercolationTest {
	Percolation percolation;
	public double p;
	public int n;
	
	@Before
	public void setUp(){
		percolation = new Percolation();
	}

	@Test
	public void testGround() {
		assertTrue(percolation.ground(3, 0)[0][0] == 0);
		assertTrue(percolation.ground(3, 1)[2][2] == 1);
		}

	
	@Test
	public void testSeep() {
		int[][] ground = {{1, 1, 2}, {1, 0, 0}, {0, 0, 0}};
		int[][] ground1 = {{1, 1, 2}, {1, 2, 2}, {0, 0, 0}};
		int[][] ground2 = {{1, 1, 2}, {1, 2, 2}, {2, 2, 2}};
		percolation.seep(ground, 0);
		assertArrayEquals(ground1, ground);
		percolation.seep(ground, 1);
		assertArrayEquals(ground2, ground);
	}
	

	@Test
	public void testPercolate() {
		int[][] ground = {{1, 1, 0}, {1, 0, 0}, {0, 0, 0}};
		int[][] ground1 = {{1, 1, 0}, {1, 1, 1}, {0, 0, 0}};
		assertTrue(percolation.percolate(ground));
		assertFalse(percolation.percolate(ground1));
	}
}
