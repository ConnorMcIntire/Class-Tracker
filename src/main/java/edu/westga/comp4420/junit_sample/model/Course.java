package edu.westga.comp4420.junit_sample.model;

import java.util.List;
import java.util.ArrayList;

public class Course {
	
	private String prefix;
	private String number;
	private String semester;
	private String notes;
	
	private static List<Course> courseList = new ArrayList<>();
	
	public Course(String prefix, String number, String semester, String notes) {
		if (prefix == null || number == null || semester == null || prefix.isEmpty() || number.isEmpty() || semester.isEmpty()) {
			throw new IllegalArgumentException("Prefix, Number, and Semester are needed");
		}
		
		if (!this.isValidSemester(semester)) {
			throw new IllegalArgumentException("Invalid format must be yy-term. (e.g fall 2025 would be 2503).");
		}
		
		this.prefix = prefix;
		this.number = number;
		this.semester = semester;
		this.notes = notes;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public String getSemester() {
		return this.semester;
	}
	
	public String getNotes() {
		return this.notes;
	}
	
	
	/**
	*Adds course to the courseList
	*@return true/false returns true or false
	*/
	public boolean addCourse() {
		if (isDuplicate(this.prefix, this.number, this.semester)) {
			return false;
		}
		courseList.add(this);
		return true;
	}
	
	
	public static List<Course> getAllCourses() {
		return new ArrayList<>(courseList);
	}
	
	public static void clearCourses() {
		courseList.clear();
	}
	
	/**
     * Makes sure a course is not a duplicate
     * 
     * @param prefix The prefix of a course
	 * @param number The number of a course
	 * @param semester The semester of a course
	 * 
     */
	private static boolean isDuplicate(String prefix, String number, String semester) {
		for (Course course : courseList) {
			if (course.getPrefix().equals(prefix) && course.getNumber().equals(number) && course.getSemester().equals(semester)) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * Makes sure a semester has the proper format
     * 
	 * @param semester The semester of a course
     */
	private boolean isValidSemester(String semester) {
		return semester.matches("\\d{4}") && Integer.parseInt(semester.substring(2)) >= 1 && Integer.parseInt(semester.substring(2)) <= 3;
	}
	
	/**
     * finds a course by its full name
     * 
     * @param courseName the full name of a course
	 * @return course/null returns the course or null
     */
	public static Course findCourseByName(String courseName) {
		for (Course course : courseList) {
			if (course.toString().equals(courseName)) {
				return course;
			}
		}
		return null;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String toString() {
		return this.prefix + " " + this.number + " " + this.semester;
	}
}
