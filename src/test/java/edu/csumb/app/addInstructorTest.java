package edu.csumb.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class addInstructorTest {

	@Test
	public void test() {
		School scd = new School("SCD");
		assertTrue(scd.addInstructor(700, "E.Tao", "tao@csumb.edu", "777-777-1234"));
		assertTrue(scd.addInstructor(100, "Y.Byun", "ybun@csumb.edu", "111-111-1111"));
		assertFalse(scd.addInstructor(700, "D. Diaz", "diazjfdaniel@gmail.com", "8312109786"));
		assertTrue(2 == scd.numOfInstructors());
	}

}