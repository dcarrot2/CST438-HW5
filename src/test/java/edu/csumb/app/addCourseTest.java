package edu.csumb.app;
import static org.junit.Assert.*;
import org.junit.Test;

public class addCourseTest {

	@Test
	public void test() {
		School scd = new School("SCD");
		assertTrue(scd.addInstructor(700, "E.Tao", "tao@csumb.edu", "777-777-1234"));
		assertTrue(scd.addCourse(300, "CST300 - Pro Sem", 700, "MLC110"));
		assertFalse(scd.addCourse(300, "Internet programming", 700, "MLC118"));
		assertTrue(1 == scd.numOfCourses());
		
	}

}