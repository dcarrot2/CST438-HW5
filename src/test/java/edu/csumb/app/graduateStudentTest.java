package edu.csumb.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class graduateStudentTest {

	@Test 
	public void testGraduation() {
		School scd = new School("SCD");
		assertTrue(scd.addInstructor(700, "E.Tao", "tao@csumb.edu", "777-777-1234"));
		assertTrue(scd.addCourse(300, "CST300 - Pro Sem", 700, "MLC110"));
		assertTrue(scd.addStudent(7777, "Alice Otter", 300, 90, "A"));
		assertTrue(scd.addStudent(2341, "Bob Otter", 300, 74, "C"));
		assertTrue(scd.graduateStudent(7777));
		assertTrue(1 == scd.numOfStudents());
		assertTrue(scd.graduateStudent(2341));
		assertTrue(0 == scd.numOfStudents());
		assertFalse(scd.graduateStudent(7777));
		assertTrue(0 == scd.numOfStudents());
	}

}