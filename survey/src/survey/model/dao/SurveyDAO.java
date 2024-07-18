package survey.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.model.dto.MemberDTO;
import projectMybatis.mybatisConfig.MybatisManager;
import survey.model.dto.SurveyDTO;

public class SurveyDAO {
	
	//설문조사 총레코드 수 
	public int total() {
		Map<String,Object> map = new HashMap<>();
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.selectOne("survey.totalsurvey",map);
		session.close();
		return result;
	}
	
	//설문조사참여 총인원 수 
	public int total_survey_user(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.selectOne("survey.total_survey_user",map);
		session.close();
		return result;
	}
	
	
	//설문조사 리스트
	public List list(String searchOption,String searchData,int firstrow,int lastrow) {
		Map<String,Object> map = new HashMap<>();
		map.put("firstrow",firstrow);
		map.put("lastrow",lastrow);
		map.put("searchOption",searchOption);
		map.put("searchData",searchData);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list =  session.selectList("survey.surveylist",map);
		session.close();
		return list;
	}

	//설문조사 통계 리스트
	public List list_statistics(int firstrow,int lastrow,SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("firstrow",firstrow);
		map.put("lastrow",lastrow);
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list =  session.selectList("survey.list_statistics",map);
		session.close();
		return list;
	}
	
	//설문조사 작성 insert
	public int insert(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.insert("survey.surveyinsert",map);
		session.commit();
		session.close();
		return result;
	}//설문조사 고유번호
	public int survey_no() {
		Map<String,Object> map = new HashMap<>();
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.selectOne("survey.seq_survey_survey_no",map);
		session.close();
		return result;
	}
	
	//설문조사 질문 insert
	public int insertsurvey_question(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.insert("survey.survey_questioninsert",map);
		session.commit();
		session.close();
		return result;
	}
	//설문조사 질문고유번호 뽑기
	public int survey_question_no() { 
		Map<String,Object> map = new HashMap<>();
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.selectOne("survey.seq_survey_question_no",map);
		session.close();
		return result;
	}
	
	//설문조사 문항 insert
	public int insertsurvey_answer(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.insert("survey.survey_answerinsert",map);
		session.commit();
		session.close();
		return result;
	}
	//설문조사 문항 고유번호 뽑기 
	public int survey_answer_no() {
		Map<String,Object> map = new HashMap<>();
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.selectOne("survey.survey_answer_no",map);
		session.close();
		return result;
	}
	
	//설문조사 참여회원 insert
	public int survey_user(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.insert("survey.survey_user",map);
		session.commit();
		session.close();
		return result;
	}
	
	//설문조사 상세보기(참여하기)
	public SurveyDTO selectsurvey(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		SurveyDTO returndto =  session.selectOne("survey.selectsurvey",map);
		session.close();
		return returndto;
	}
	//설문조사 질문 출력
	public List<SurveyDTO> selectsurvey_question(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveyDTO> returnlist=  session.selectList("survey.selectsurvey_question",map);
		session.close();
		return returnlist;
	}
	//설문조사 질문별 문항 출력 
	public List<SurveyDTO> selectsurvey_answer(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveyDTO> returnlist=  session.selectList("survey.selectsurvey_answer",map);
		session.close();
		return returnlist;
	}
	
	//
	public List<SurveyDTO> answer_count(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveyDTO> returnlist=  session.selectList("survey.answer_count",map);
		session.close();
		return returnlist;
	}
	//설문조사 참여 내역
	public List history(int firstrow,int lastrow,SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("firstrow",firstrow);
		map.put("lastrow",lastrow);
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list =  session.selectList("survey.history",map);
		session.close();
		return list;
	}
	
	//포인트 적립 
	public int insert_point(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.insert("survey.insert_point",map);
		session.commit();
		session.close();
		return result;
	}
	
	//포인트 적립 조회
	public List accumulate(int firstrow,int lastrow,SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("firstrow",firstrow);
		map.put("lastrow",lastrow);
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list =  session.selectList("survey.accumulate",map);
		session.close();
		return list;
	}
	
	
	//자신이 참여한 설문조사 번호
	public List participationCompleted(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list =  session.selectList("survey.participationCompleted",map);
		session.close();
		return list;
	}
	
	//1:1문의 작성
	public int insert_inquiry(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.insert("survey.insert_inquiry",map);
		session.commit();
		session.close();
		return result;
	}
	//1:1문의 리스트 
	public List list_inquiry(int firstrow,int lastrow,SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		map.put("firstrow",firstrow);
		map.put("lastrow",lastrow);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveyDTO> list =  session.selectList("survey.list_inquiry",map);
		session.close();
		return list;
	}
	
	//1:1문의 상세보기
	public SurveyDTO inquiry_select(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		SurveyDTO returndto = session.selectOne("survey.inquiry_select",map);
		session.close();
		return returndto;
	}
	//1:1문의 수정
	public int inquiry_update(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.update("survey.inquiry_update",map);
		session.commit();
		session.close();
		return result;
	}
	
	//1:1문의 삭제
	public int inquiry_delete(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.delete("survey.inquiry_delete",map);
		session.commit();
		session.close();
		
		return result;
	}
	//1:1문의 답변
	public int inquiry_answer_insert(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.insert("survey.inquiry_answer_insert",map);
		session.commit();
		session.close();
		return result;
	}
	//1:1문의 처리상태 답변완료로변경
	public void inquiry_complete_update(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("survey.inquiry_complete_update",map);
		session.commit();
		session.close();
		}
	//1:1문의답변 뽑아오기
	public String inquiry_answer(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		String returnString = session.selectOne("survey.inquiry_answer",map);
		session.close();
		return returnString;
	}
	//설문조사 삭제칼럼에 삭제추가
	public int survey_delete(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.update("survey.survey_delete",map);
		session.commit();
		session.close();
		return result;
	}
	
	//설문조사 통계 남여비율 
	public List gender_count(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveyDTO> list =  session.selectList("survey.gender_count",map);
		session.close();
		return list;
	}
	
	//설문조사 통계 연령대별 비율
	public List age_count(SurveyDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list =  session.selectList("survey.age_count",map);
		session.close();
		return list;
	}
	
	
}
