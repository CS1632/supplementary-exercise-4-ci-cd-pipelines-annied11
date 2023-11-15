package edu.pitt.cs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	@Before
	public void setUp() throws Exception {
		// Config.setBuggyRentACat(true);


		Cat.bugInjectionOn = true;
		r = RentACat.createInstance();
	
		// Creating mock instances for Cat objects
		c1 = Mockito.mock(Cat.class);
		Mockito.when(c1.getId()).thenReturn(1);
		Mockito.when(c1.getName()).thenReturn("Jennyanydots");
		Mockito.when(c1.getRented()).thenReturn(false);

		c2 = Mockito.mock(Cat.class);
		Mockito.when(c2.getId()).thenReturn(2);
		Mockito.when(c2.getName()).thenReturn("Old Deuteronomy");
		Mockito.when(c2.getRented()).thenReturn(false);

		c3 = Mockito.mock(Cat.class);
		Mockito.when(c3.getId()).thenReturn(3);
		Mockito.when(c3.getName()).thenReturn("Mistoffelees");
		Mockito.when(c3.getRented()).thenReturn(false);

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
	}
	

	@After
	public void tearDown() throws Exception {
		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 * </pre>
	 */

	 @Test
	public void testGetCatNullNumCats0() {
		r = RentACat.createInstance(); // Recreate r to make sure it has no cats
    	assertNull(r.getCat(2));
}
	 

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2.
	 * </pre>
	 */

	@Test
	public void testGetCatNumCats3() {
		Cat result = r.getCat(2);
		assertNotNull(result);
		assertEquals(2, result.getId());
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatAvailableFalseNumCats0() {
		r = RentACat.createInstance(); // Recreate r to make sure it has no cats
		assertFalse(r.catAvailable(2));
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c3 is rented.
	 *                c1 and c2 are not rented.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is true.
	 * </pre>
	 */

	@Test
	public void testCatAvailableTrueNumCats3() {
		Mockito.when(c3.getRented()).thenReturn(true);

    	assertTrue(r.catAvailable(2));
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 *                c1 and c3 are not rented.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatAvailableFalseNumCats3() {
		Mockito.when(c2.getRented()).thenReturn(true); // Set c2 as rented

    	assertFalse(r.catAvailable(2)); // Since c2 is rented, availability should be false
	}

	/**
	 * Test case for boolean catExists(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call catExists(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatExistsFalseNumCats0() {
		r = RentACat.createInstance(); // Recreate r to make sure it has no cats

    	assertFalse(r.catExists(2)); // Since there are no cats in r, the return value should be false	
	}
	

	/**
	 * Test case for boolean catExists(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call catExists(2).
	 * Postconditions: Return value is true.
	 * </pre>
	 */

	@Test
	public void testCatExistsTrueNumCats3() {
		assertTrue(r.catExists(2)); // Since c2 was added to r in the setUp method, the return value should be true
	}


	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */

	@Test
	public void testListCatsNumCats0() {
		r = RentACat.createInstance(); // Recreate r to make sure it has no cats

    	assertEquals("", r.listCats()); // Since there are no cats in r, the return value should be an empty string
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */

	 @Test
	 public void testListCatsNumCats3() {
		 // 创建 RentACat 实例
		 RentACat r = RentACat.createInstance();
	 
		 // 创建 Mock 对象
		 Cat cat1 = mock(Cat.class);
		 Cat cat2 = mock(Cat.class);
		 Cat cat3 = mock(Cat.class);
	 
		 // 设置 Mock 对象的行为
		 when(cat1.toString()).thenReturn("ID 1. Jennyanydots");
		 when(cat2.toString()).thenReturn("ID 2. Old Deuteronomy");
		 when(cat3.toString()).thenReturn("ID 3. Mistoffelees");
	 
		 // 添加 Mock 对象到 RentACat 实例中
		 r.addCat(cat1);
		 r.addCat(cat2);
		 r.addCat(cat3);
	 
		 // 构建期望的字符串
		 String expected = "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n";
	 
		 // 断言
		 assertEquals(expected, r.listCats());
	 }
	 

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testRentCatFailureNumCats0() {
		r = RentACat.createInstance(); // Recreate r to make sure it has no cats
    	assertFalse(r.rentCat(2)); // Since there are no cats in r, the return value should be false
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c1.rentCat(), c2.rentCat(), c3.rentCat() are never called.
	 * </pre>
	 * 
	 * Hint: See sample_code/mockito_example/NoogieTest.java in the course
	 * repository for an example of behavior verification. Refer to the
	 * testBadgerPlayCalled method.
	 */

	@Test
	public void testRentCatFailureNumCats3() {
		RentACat r = RentACat.createInstance();

		Cat c1 = mock(Cat.class);
		Cat c2 = mock(Cat.class);
		Cat c3 = mock(Cat.class);

		when(c2.getRented()).thenReturn(true);

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		assertFalse(r.rentCat(2));

		verify(c1, never()).rentCat();
		verify(c2, never()).rentCat();
		verify(c3, never()).rentCat();
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testReturnCatFailureNumCats0() {
		RentACat r = RentACat.createInstance();
    	assertFalse(r.returnCat(2));
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2.returnCat() is called exactly once.
	 *                 c1.returnCat() and c3.returnCat are never called.
	 * </pre>
	 * 
	 * Hint: See sample_code/mockito_example/NoogieTest.java in the course
	 * repository for an example of behavior verification. Refer to the
	 * testBadgerPlayCalled method.
	 */

	 @Test
	 public void testReturnCatNumCats3() {
		 // Instantiate the RentACat instance
		 RentACat r = RentACat.createInstance();
	 
		 // Create mock cats
		 Cat c1 = mock(Cat.class);
		 Cat c2 = mock(Cat.class);
		 Cat c3 = mock(Cat.class);
	 
		 // Specify return values for getId() for our mocks
		 when(c1.getId()).thenReturn(1);
		 when(c2.getId()).thenReturn(2);
		 when(c3.getId()).thenReturn(3);
	 
		 // Specify that c2 is rented
		 when(c2.getRented()).thenReturn(true);
	 
		 // Add cats to RentACat instance
		 r.addCat(c1);
		 r.addCat(c2);
		 r.addCat(c3);
	 
		 // 2. Execute
		 boolean result = r.returnCat(2);
	 
		 // 3. Verify
		 assertTrue(result); // Postcondition 1: Return value is true
		 verify(c2, times(1)).returnCat(); // Postcondition 2: c2.returnCat() is called exactly once
		 verify(c1, never()).returnCat();  // Postcondition 3: c1.returnCat() is never called
		 verify(c3, never()).returnCat();  // Postcondition 3: c3.returnCat() is never called
	 }
	 
}
