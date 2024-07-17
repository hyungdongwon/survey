package member.model.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.model.dto.MemberDTO;
import projectMybatis.mybatisConfig.MybatisManager;
import survey.model.dto.SurveyDTO;

public class MemberDAO {
	
	//회원 총 레코드수
	public int total() {
		Map<String,Object> map = new HashMap<>();
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.selectOne("survey.total",map);
		session.close();
		return result;
	}
	
	//로그인
	public MemberDTO login(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO returndto =  session.selectOne("survey.login",map);
		session.close();
		if(returndto == null) {
			MemberDTO faildto = new MemberDTO();
			return faildto;
		}
		return returndto;
	}
	
	//회원목록(관리자)
	public List list(int firstrow,int lastrow) {
		Map<String,Object> map = new HashMap<>();
		map.put("firstrow",firstrow);
		map.put("lastrow",lastrow);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list =  session.selectList("survey.list",map);
		session.close();
		return list;
	}
	
	//회원가입
	public int insert(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.insert("survey.insert",map);
		session.commit();
		session.close();
		return result; 
	}
	//회원가입 아이디체크
	public String idcheck(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		String returndto =  session.selectOne("survey.idcheck",map);
		session.close();
		return returndto;
	}
	
	//회원 상세보기
	public MemberDTO select(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO returndto =  session.selectOne("survey.select",map);
		session.close();
		return returndto;
	}
	
	//회원수정
	public int update(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.update("survey.update",map);
		session.commit();
		session.close();
		
		return result;
	}
	
	//회원탈퇴
	public int delete(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.delete("survey.delete",map);
		session.commit();
		session.close();
		
		return result;
	}
	
	//비밀번호 변경
	public int passwordupdate(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>();
		map.put("dto",dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result =  session.update("survey.passwordupdate",map);
		session.commit();
		session.close();
		
		return result;
	}
	
	
	
	
	
	
}
