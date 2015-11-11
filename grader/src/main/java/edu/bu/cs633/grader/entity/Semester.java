package edu.bu.cs633.grader.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents a Semester in
 * 
 * @author donlanp
 * 
 */
@Entity
@Table(name = "semester")
public class Semester {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int semesterId;

	private String semesterName;
	private int year;
	
	@OneToMany(mappedBy = "semester")
	private List<CourseSemester> courseSemesters;
	
	public Semester(){
		courseSemesters = new ArrayList<CourseSemester>();
	}
	
	public String toString(){
		return year + "-" + semesterName;
	}
	
	//Getters and Setters

	/**
	 * @return the semesterId
	 */
	public int getSemesterId() {
		return semesterId;
	}

	/**
	 * @param semesterId
	 *            the semesterId to set
	 */
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	/**
	 * @return the semesterName
	 */
	public String getSemesterName() {
		return semesterName;
	}

	/**
	 * @param semesterName
	 *            the semesterName to set
	 */
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

}
