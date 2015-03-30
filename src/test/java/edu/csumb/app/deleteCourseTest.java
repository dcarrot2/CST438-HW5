package edu.csumb.app;
import static org.junit.Assert.*;

import org.junit.Test;

public class deleteCourseTest {

	@Test
	public void test() {
		School scd = new School("SCD");
		assertTrue(scd.addInstructor(700, "E.Tao", "tao@csumb.edu", "777-777-1234"));
		assertTrue(scd.addCourse(300, "CST300 - Pro Sem", 700, "MLC110"));
		assertTrue(1 == scd.numOfCourses());
		assertTrue(scd.deleteCourse(300));
		assertTrue(0 == scd.numOfCourses());
		
	}

}