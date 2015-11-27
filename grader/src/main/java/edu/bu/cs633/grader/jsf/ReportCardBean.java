package edu.bu.cs633.grader.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;

import edu.bu.cs633.grader.entity.Enrollment;
import edu.bu.cs633.grader.entity.helper.ReportCard;
import edu.bu.cs633.grader.service.AssignmentService;
import edu.bu.cs633.grader.service.CourseService;

@ManagedBean(name="reportCardBean")
@RequestScoped
public class ReportCardBean {

	
	@Autowired
	private UserSessionBean currentUser;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private AssignmentService assignmentService;
	
	private List<ReportCard> reportCards;
	
	@PostConstruct
	public void init(){
		//We know the user is a student...
		
		List<Enrollment> enrollments = courseService.getStudentEnrollments(currentUser.getCurrentUser().getStudent());
		
		setReportCards(new ArrayList<ReportCard>());
		for(Enrollment e: enrollments){
			getReportCards().add(new ReportCard(e.getCourseSemester(), assignmentService.getGradesForEnrollment(e)));
		}
		
	}
	
	// Getters and Setters

	/**
	 * @return the currentUser
	 */
	public UserSessionBean getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(UserSessionBean currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the reportCards
	 */
	public List<ReportCard> getReportCards() {
		return reportCards;
	}

	/**
	 * @param reportCards the reportCards to set
	 */
	public void setReportCards(List<ReportCard> reportCards) {
		this.reportCards = reportCards;
	}
}
