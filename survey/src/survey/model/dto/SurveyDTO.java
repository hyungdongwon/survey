package survey.model.dto;

import java.util.Date;
import java.util.List;

public class SurveyDTO {
	//survey
	private int survey_no ;
	private String member_id ;
	private String survey_name ;
	private String survey_content; 
	private String survey_date ;
	private String survey_end ;
	private String survey_point; 
	private String survey_filename; 
	
	//survey_question
	private int survey_question_no; 
	private String question_no;
	
	//survey_answer
	private int survey_answer_no;
	private String survey_answer_content;
	
	//통계 자료 
	private int answer_count;
	
	//survey_user
	private String survey_user_date;
	
	//survey_point
	private int survey_point_getpoint;
	private String survey_point_getdate;
	
	//inquire
	private int inquiry_no;
	private String inquiry_subject;
	private String inquiry_type;
	private String inquiry_content;
	private String inquiry_date;
	private String inquiry_complete;
	private String inquiry_disclosure;
	
	private String member_name;
	
	//inquire_answer
	private int inquiry_answer_no;
	private String inquiry_answer;
	private String inquiry_answer_date;
	
	
	
	public int getInquiry_answer_no() {
		return inquiry_answer_no;
	}
	public void setInquiry_answer_no(int inquiry_answer_no) {
		this.inquiry_answer_no = inquiry_answer_no;
	}
	public String getInquiry_answer() {
		return inquiry_answer;
	}
	public void setInquiry_answer(String inquiry_answer) {
		this.inquiry_answer = inquiry_answer;
	}
	public String getInquiry_answer_date() {
		return inquiry_answer_date;
	}
	public void setInquiry_answer_date(String inquiry_answer_date) {
		this.inquiry_answer_date = inquiry_answer_date;
	}
	public String getInquiry_date() {
		return inquiry_date;
	}
	public void setInquiry_date(String inquiry_date) {
		this.inquiry_date = inquiry_date;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getInquiry_disclosure() {
		return inquiry_disclosure;
	}
	public void setInquiry_disclosure(String inquiry_disclosure) {
		this.inquiry_disclosure = inquiry_disclosure;
	}
	public int getInquiry_no() {
		return inquiry_no;
	}
	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}
	public String getInquiry_subject() {
		return inquiry_subject;
	}
	public void setInquiry_subject(String inquiry_subject) {
		this.inquiry_subject = inquiry_subject;
	}
	public String getInquiry_type() {
		return inquiry_type;
	}
	public void setInquiry_type(String inquiry_type) {
		this.inquiry_type = inquiry_type;
	}
	public String getInquiry_content() {
		return inquiry_content;
	}
	public void setInquiry_content(String inquiry_content) {
		this.inquiry_content = inquiry_content;
	}
	
	public String getInquiry_complete() {
		return inquiry_complete;
	}
	public void setInquiry_complete(String inquiry_complete) {
		this.inquiry_complete = inquiry_complete;
	}
	public int getSurvey_point_getpoint() {
		return survey_point_getpoint;
	}
	public void setSurvey_point_getpoint(int survey_point_getpoint) {
		this.survey_point_getpoint = survey_point_getpoint;
	}
	public String getSurvey_point_getdate() {
		return survey_point_getdate;
	}
	public void setSurvey_point_getdate(String survey_point_getdate) {
		this.survey_point_getdate = survey_point_getdate;
	}
	public String getSurvey_user_date() {
		return survey_user_date;
	}
	public void setSurvey_user_date(String survey_user_date) {
		this.survey_user_date = survey_user_date;
	}
	public int getAnswer_count() {
		return answer_count;
	}
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}
	public int getSurvey_no() {
		return survey_no;
	}
	public void setSurvey_no(int survey_no) {
		this.survey_no = survey_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getSurvey_name() {
		return survey_name;
	}
	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}
	public String getSurvey_content() {
		return survey_content;
	}
	public void setSurvey_content(String survey_content) {
		this.survey_content = survey_content;
	}
	public String getSurvey_date() {
		return survey_date;
	}
	public void setSurvey_date(String survey_date) {
		this.survey_date = survey_date;
	}
	public String getSurvey_end() {
		return survey_end;
	}
	public void setSurvey_end(String survey_end) {
		this.survey_end = survey_end;
	}
	public String getSurvey_point() {
		return survey_point;
	}
	public void setSurvey_point(String survey_point) {
		this.survey_point = survey_point;
	}
	public String getSurvey_filename() {
		return survey_filename;
	}
	public void setSurvey_filename(String survey_filename) {
		this.survey_filename = survey_filename;
	}
	
	public String getQuestion_no() {
		return question_no;
	}
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}
	
	public String getSurvey_answer_content() {
		return survey_answer_content;
	}
	public void setSurvey_answer_content(String survey_answer_content) {
		this.survey_answer_content = survey_answer_content;
	}
	public int getSurvey_question_no() {
		return survey_question_no;
	}
	public void setSurvey_question_no(int survey_question_no) {
		this.survey_question_no = survey_question_no;
	}
	public int getSurvey_answer_no() {
		return survey_answer_no;
	}
	public void setSurvey_answer_no(int survey_answer_no) {
		this.survey_answer_no = survey_answer_no;
	}
	
	
	
	
	
	
	
	
}
