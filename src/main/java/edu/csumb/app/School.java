package edu.csumb.app;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class School
{
    private String name;
	private Scanner reader;
	private static final int READ_INSTRUCTORS = 0; // constants for better style
													// of switch statement
	private static final int READ_COURSES = 1;
	private static final int READ_STUDENTS = 2;
	private ArrayList<Instructor> instructors = null; // hold instructors
	private ArrayList<Course> courses = null; // hold courses
	private ArrayList<Student> students = null;

	/**
	 * Constructor
	 * 
	 * @param name
	 */

	public School(String name) {
		this.name = name;
		instructors = new ArrayList<Instructor>();
		courses = new ArrayList<Course>();
		students = new ArrayList<Student>();

	}

	public int numOfStudents(){
		return students.size();
	}
	
	public int numOfCourses(){
		return courses.size();
	}

	public int numOfInstructors(){
		return instructors.size();
	}
	
	/**
	 * Read textfile by passing in a path Creates objects and adds them to
	 * corresponding list
	 * 
	 * @param filePath
	 */

	public void readData(String filePath) {
		String[] line = null; // hold current string read
		int addMode = 0; // read instructors, courses or students
		try {
			File file = new File(filePath);
			file = new File(file.getAbsolutePath());
			reader = new Scanner(file);

			while (reader.hasNext()) {
				
				int amount = reader.nextInt();

				for (int i = 0; i <= amount; i++) {
					line = reader.nextLine().split(","); // split string by
															// commas
					// what objects are we adding?
					optionAddMode(line, addMode);
				}
				addMode += 1; // we're done adding a group of data, let's move
								// to the next
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean optionAddMode(String[] data, int addMode) {
		switch (addMode) {
		case READ_INSTRUCTORS:
			if (data.length > 1) {
				addInstructor(Integer.parseInt(data[0]), data[1], data[2],
						data[3]);
			}
			return true;
		case READ_COURSES:
			if (data.length > 1) {
				addCourse(Integer.parseInt(data[0]), data[1],
						Integer.parseInt(data[2]), data[3]);
			}
			return true;
		case READ_STUDENTS:
			if (data.length > 1) {
				addStudent(Integer.parseInt(data[0]), data[1],
						Integer.parseInt(data[2]), Double.parseDouble(data[3]),
						data[4]);
			}
			return true;
		}
		return false;
	}

	/**
	 * print information about the school
	 */
	public void schoolInfo() {
		System.out.println("School name: " + name);
		System.out.println("Instructor Information");
		for (Instructor instructor : instructors) {
			System.out.println("\t" + instructor);
		}
		System.out.println("Course Information");
		for (Course course : courses) {
			System.out.println("\t" + course);
		}
		System.out.println("Student Information");
		for (Student student : students) {

			System.out.print(student);
			System.out.println(getCourse(student.getCourseEnrolled())); // get
																		// the
																		// course
																		// enrolled
																		// for
																		// the
																		// 'dummy'
																		// object
		}
	}

	/**
	 * add instructor to school
	 * 
	 * @param employeeNumber
	 * @param name
	 * @param email
	 * @param phoneNumber
	 */

	public boolean addInstructor(int employeeNumber, String name, String email,
			String phoneNumber) {
		for (Instructor instructor : instructors) {
			if (instructor.equals(employeeNumber)) {
				System.out
						.println("Instructor addition failed - Duplicated employee number.");
				return false;
			}
		}
		Instructor instructor = new Instructor(employeeNumber, name, email,
				phoneNumber);
		instructors.add(instructor);
		return true;
	}

	/**
	 * add course to school
	 * 
	 * @param courseNumber
	 * @param courseTitle
	 * @param employeeNumber
	 * @param classRoom
	 */
	public boolean addCourse(int courseNumber, String courseTitle,
			int employeeNumber, String classRoom) {
		for (Course course : courses)
			if (course.equals(courseNumber)) {
				System.out
						.println("Course addition failed - Duplicated course number.");
				return false;
			}

		for (Instructor instructor : instructors) {
			if (instructor.equals(employeeNumber)) {
				Course course = new Course(courseNumber, courseTitle,
						employeeNumber, classRoom);
				courses.add(course);
				return true;
			}
		}
		System.out.println("Employee does not exist");
		return false;
	}

	/**
	 * add Student to class. If the student was already created, add course to
	 * the first instance of student and add the new instance of the student to
	 * our "dummy" student list
	 * 
	 * @param identification
	 * @param name
	 * @param courseEnrolled
	 * @param finalScore
	 * @param letterGrade
	 */
	public boolean addStudent(int identification, String name, int courseEnrolled,
			double finalScore, String letterGrade) {

		for (Student student : students) {
			if (student.equals(identification)) {
				student.addCourse(courseEnrolled, finalScore,
						letterGrade.charAt(0));
				Student newStudent = new Student(identification,
						courseEnrolled, name, finalScore, letterGrade.charAt(0));

				try {
					getCourse(courseEnrolled).addStudent(newStudent);
					students.add(newStudent);
				} catch (NullPointerException e) {
					System.out
							.println("The course does not exist, student not added");
					return false;
				}
				return true;
			}
		}
		Student student = new Student(identification, courseEnrolled, name,
				finalScore, letterGrade.charAt(0));
		try {
			students.add(student);
			getCourse(courseEnrolled).addStudent(student);
		} catch (NullPointerException e) {
			System.out.println("The course does not exist, student not added");
			return false;
		}
		return true;
	}

	/**
	 * Find course by coursenumber
	 * 
	 * @param course
	 * @return course if found
	 */
	public Course getCourse(int courseNumber) {
		for (Course findCourse : courses)
			if (findCourse.equals(courseNumber))
				return findCourse;
		return null;
	}

	/**
	 * print information of all courses
	 */
	public void courseInfo() {
		System.out.println("Number of courses: " + courses.size());
		for (Course course : courses) {
			System.out.println(course.getCourseNumber() + ": "
					+ course.courseSize() + " enrolled");
		}
	}

	/**
	 * print courseInfo of a specific course
	 * 
	 * @param courseNumber
	 */
	public void courseInfo(int courseNumber) {

		for (Course course : courses) {
			if (course.equals(courseNumber)) {
				System.out.println("Course Number: " + courseNumber);
				for (Instructor instructor : instructors)
					if (instructor.equals(course.getEmployeeNumber())) {
						System.out.println("Instructor: " + instructor);
						break;
					}
				System.out.println("Course Title: " + course);
				System.out.println("Room: " + course.getClassRoom());
				System.out.println("Enrolled Students: ");
				for (Student students : course.getStudents())
					System.out.println("\t" + students.getName() + " "
							+ students.getGrade(courseNumber));

				System.out.printf("Course average: %4.2f", course.getAverage());
				System.out.println();

			}
		}
	}

	/**
	 * get Student information by matching id
	 * 
	 * @param identification
	 */

	public void studentInfo(int identification) {
		System.out.println("Student number: " + identification);
		for (Student student : students) {
			if (student.equals(identification)) {
				student.printStudentProfile();
				return;
			}
		}

		System.out.println("Not exist!");
	}

	/**
	 * delete course if there are no students enrolled
	 * 
	 * @param courseNumber
	 */
	public boolean deleteCourse(int courseNumber) {
		if (getCourse(courseNumber).courseSize() > 0) {
			System.out
					.println("Cannot delete course if there's a student enrolled in the course");
			return false;
		}
		courses.remove(getCourse(courseNumber));
		return true;
	}

	/**
	 * Graduate the student and delete their attendance from all courses
	 * 
	 * @param identification
	 */
	public boolean graduateStudent(int identification) {
		boolean purged = false;
		Iterator<Student> iter = students.iterator();

		while (iter.hasNext()) {
			Student student = iter.next();
			if (student.equals(identification) && !purged) {
				System.out.println("Purging");
				for (Course course : courses) {
					course.findStudent(student.getIdentification());
				}
				iter.remove();
				purged = true;
			} else if (student.equals(identification)) {
				iter.remove();
			}
		}
		return purged;
	}
}
