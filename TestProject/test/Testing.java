package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.rules.ExpectedException;


class Testing {
	
	@Rule public ExpectedException thrown = ExpectedException.none();

	@Test
	void concatening() {
		Junit test = new Junit();
		String result = test.ConcatenatenName("one", "two");
		assertEquals(result, "onetwo");	
	}
	
	@Test
	void concateningbasic2() {
		Junit test = new Junit();
		String result = test.ConcatenatenName("three", "four");
		assertEquals(result, "threefour");	
		assertNotEquals(result, "not");
	}
	
	@Test
	void test() {
		Junit test = new Junit();
		try {
			int result = test.multiply(3, 4);
			assertEquals(result, 12);
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	
	@Test
	void testreturnone() {
		Junit test = new Junit();
		try {
			assumeFalse(test.returnone());
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	
	@Test
	void testmultiply2() {
		Junit test = new Junit();
		try {
			int result = test.multiply(0, 4);
			assertEquals(result, 0);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	void shouldThrowException() {
		Junit test = new Junit();
		boolean thrown = false;
		try {
			int result = test.multiply(-1, 4);
		} catch (Exception e) {
			thrown = true;
			assertEquals(e.getMessage(), "-1");
		}
		
		assertTrue(thrown);	
		
	}
	
	@Test
	public void empty() { 
		Assertions.assertThrows(Exception.class, new Executable() {
			@Override
	        public void execute() throws Throwable {
				Junit test = new Junit();
	            test.multiply(-1, 2);
	        }
	    });
	}
	
	@Test
	public void emptywithlambdafunctions() { 
		Assertions.assertThrows(Exception.class, () -> {
			Junit test = new Junit();
            test.multiply(-1, 2);
	    });
	}
	
	

}
