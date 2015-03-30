package edu.csumb.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Student {
	private int identification;
	private int courseEnrolled;
	private String name;
	
	private ArrayList<Integer> courses = null;
	private HashMap<Integer, Grade> grades = null;
	
	public Student(int identification, int courseEnrolled, String name, double finalScore, char letterGrade){
		this.identification = identification;
		this.courseEnrolled = courseEnrolled;
		this.name = name;
		courses = new ArrayList<Integer>();
		grades = new HashMap<Integer, Grade>();
		courses.add(courseEnrolled);
		Grade grade = new Grade(finalScore, letterGrade);
		grades.put(courseEnrolled, grade);
		
	}
	
	public boolean equals(int identification){
		return this.identification == identification;
	}

	@Override
	public String toString() {
		return "\t" + name + ": ";
	}
	
	public String getName() {
		return name;
	}
	
	public Grade getGrade(int courseNumber){
		if(grades.containsKey(courseNumber))
			return grades.get(courseNumber);
		return null;
	}
	 
	public void addCourse(int courseNumber, double finalScore, char letterGrade){
		if(courses.contains(courseNumber)){
			return;
		}
		courses.add(courseNumber);
		Grade grade = new Grade(finalScore, letterGrade);
		grades.put(courseNumber, grade); 
	}
	
	public void printStudentProfile(){
		double average = 0;
		System.out.println("Name: " + this.name);
		Set<Integer> setOfKeys = grades.keySet();
		Iterator<Integer> iterator = setOfKeys.iterator();
		while(iterator.hasNext()){
			int key = (int)iterator.next();
			System.out.print("\t" + key + ": ");
			System.out.println((Grade)grades.get(key));
			average += grades.get(key).getScore();
		}
		
		average = average / grades.size();
		System.out.printf("Course Average: %4.2f", average);
		System.out.println();
	}
	
	public int getCourseEnrolled(){
		return courseEnrolled;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (courseEnrolled != other.courseEnrolled)
			return false;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (grades == null) {
			if (other.grades != null)
				return false;
		} else if (!grades.equals(other.grades))
			return false;
		if (identification != other.identification)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public int getIdentification(){
		return identification;
	}

}