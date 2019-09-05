package ecj;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultTest {

	@Test
	public void test1() {
		Result r1 = new Result();
		Result r2 = new Result();
		
		r1.add(1, 1);
		r1.add(2, 4);
		
		r2.add(2, 2);
		r2.add(1, 3);
		
		assertEquals(1, r1.findTimeDominates(r2), 0);
		assertEquals(1, r2.findTimeDominates(r1), 0);
	}
	
	@Test
	public void test2() {
		Result a = new Result();
		Result b = new Result();
		
		a.add(0, 1);
		a.add(1, 2);
		a.add(2, 3);
		a.add(1, 3);
		a.add(1, 4);
		a.add(2, 5);
		a.add(1, 5);
		
		b.add(3, 1);
		b.add(1, 2);
		b.add(2, 2);
		b.add(1, 6);
		
		assertEquals(7, a.findTimeDominates(b), 0);
		assertEquals(1, b.findTimeDominates(a), 0);
	}
	
	@Test
	public void test3() {
		Result a = new Result();
		Result b = new Result();
		
		a.add(0, 1);
		a.add(1, 2);
		a.add(1, 3);
		
		b.add(3, 4);
		b.add(1, 3);
		b.add(1, 2);
		b.add(1, 1);
		
		assertEquals(4, a.findTimeDominates(b), 0);
		assertEquals(1, b.findTimeDominates(a), 0);
	}

}
