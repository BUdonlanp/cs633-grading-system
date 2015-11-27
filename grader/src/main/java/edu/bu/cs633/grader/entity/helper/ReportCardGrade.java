package edu.bu.cs633.grader.entity.helper;

import java.text.DecimalFormat;

import edu.bu.cs633.grader.entity.Grade;

/**
 * @author donlanp
 * 
 */
public class ReportCardGrade {

	private static DecimalFormat roundToTwo = new DecimalFormat(".##");

	private String assignmentName;

	private int pointsGraded;
	private int pointsAvailable;
	
	public ReportCardGrade(Grade grade){
		this.assignmentName = grade.getAssignment().getAssignmentName();
		this.pointsGraded = grade.getPointsGraded();
		this.pointsAvailable = grade.getAssignment().getAssignmentTotalPoints();
	}
	
	public String getPercentage(){
		double value = ((double)pointsGraded / (double) pointsAvailable) * 100;
		
		return roundToTwo.format(value);
	}
	
	//Basic getters and Setters

	/**
	 * @return the assignmentName
	 */
	public String getAssignmentName() {
		return assignmentName;
	}

	/**
	 * @param assignmentName
	 *            the assignmentName to set
	 */
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	/**
	 * @return the pointsGraded
	 */
	public int getPointsGraded() {
		return pointsGraded;
	}

	/**
	 * @param pointsGraded
	 *            the pointsGraded to set
	 */
	public void setPointsGraded(int pointsGraded) {
		this.pointsGraded = pointsGraded;
	}

	/**
	 * @return the pointsAvailable
	 */
	public int getPointsAvailable() {
		return pointsAvailable;
	}

	/**
	 * @param pointsAvailable
	 *            the pointsAvailable to set
	 */
	public void setPointsAvailable(int pointsAvailable) {
		this.pointsAvailable = pointsAvailable;
	}

}
