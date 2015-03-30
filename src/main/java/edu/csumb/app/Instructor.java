package edu.csumb.app;

import java.util.ArrayList;

public class Instructor {

	private int employeeNumber;
	private String name;
	private String email;
	private String phoneNumber;
	private ArrayList<Course> courses = null; // list of courses taught by instructor
	
	public Instructor(int employeeNumber, String name, String email, String phoneNumber){
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		courses = new ArrayList<Course>();
	}


	@Override
	public String toString() {
		return name;
	}

	// if the same object by employeeNumber
	public boolean equals(int employeeNumber){
		return this.employeeNumber == employeeNumber;
	}
	
	// add a course to the ones taught by the instructor
	public void addCourse(Course course){
		courses.add(course);
	}
}