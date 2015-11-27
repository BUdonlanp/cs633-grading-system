package edu.bu.cs633.grader.entity.helper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import edu.bu.cs633.grader.entity.CourseSemester;
import edu.bu.cs633.grader.entity.Grade;

/**
 * Helper POJO to calculate final grades for a course
 * 
 * @author donlanp
 * 
 */
public class ReportCard {

	private static DecimalFormat roundToTwo = new DecimalFormat(".##");

	private String courseName;

	private List<ReportCardGrade> grades;

	public ReportCard(CourseSemester course, List<Grade> grades) {
		this.courseName = course.toString();

		this.setGrades(new ArrayList<ReportCardGrade>());
		for (Grade g : grades) {
			this.getGrades().add(new ReportCardGrade(g));
		}
	}

	public String getCourseName() {
		return this.courseName;
	}

	public String getFinalGrade() {
		double totalPointsAvailable = 0.0;
		double totalPointsGraded = 0.0;

		for (ReportCardGrade g : getGrades()) {
			totalPointsAvailable += g.getPointsAvailable();
			totalPointsGraded += g.getPointsGraded();
		}

		double finalPercentage = (totalPointsGraded / totalPointsAvailable) * 100;
		
		return roundToTwo.format(finalPercentage);
	}

	/**
	 * @return the grades
	 */
	public List<ReportCardGrade> getGrades() {
		return grades;
	}

	/**
	 * @param grades the grades to set
	 */
	public void setGrades(List<ReportCardGrade> grades) {
		this.grades = grades;
	}

}
