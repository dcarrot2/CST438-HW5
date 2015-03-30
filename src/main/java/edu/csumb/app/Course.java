package edu.csumb.app;

import java.util.ArrayList;
import java.util.Iterator;

public class Course {
	private int employeeNumber;
	private int courseNumber;
	private String courseTitle;
	private String classRoom;
	private ArrayList<Student> students = null;
	private double total;
	
	public Course(int courseNumber, String courseTitle, int employeeNumber, String classRoom ){
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.employeeNumber = employeeNumber;
		this.classRoom = classRoom;
		this.students = new ArrayList<Student>();
		this.total = 0;
	}

	@Override
	public String toString() {
		return courseTitle;
	}

	public boolean equals(int courseNumber){
		return this.courseNumber == courseNumber;
	}
	
	public int getCourseNumber(){
		return courseNumber;
	}
	
	public String getClassRoom(){
		return classRoom;
	}
	
	public void addStudent(Student student){
		if(!students.contains(student)){
			students.add(student);
		}
		total += student.getGrade(this.courseNumber).getScore();
	}
	

	
	public int courseSize(){
		return students.size();
	}
	
	public int getEmployeeNumber(){
		return employeeNumber;
	}
	
	public ArrayList<Student> getStudents(){
		return students;
	}
	
	public void updateLocation(String classRoom){
		this.classRoom = classRoom;
	}
	
	public double getAverage(){
		if(total == 0)
			return 0;
		return total / students.size();
	}
	
	public void findStudent(int identification){
		Iterator<Student> iter = students.iterator();
		while(iter.hasNext()){
			Student student = iter.next();
			if(student.getIdentification() == identification){
				total -= student.getGrade(courseNumber).getScore();
				iter.remove();
			}
		}
	}
	
}