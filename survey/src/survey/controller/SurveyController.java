package survey.controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.asn1.its.SequenceOfPsidGroupPermissions;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;
import org.json.simple.JSONObject;

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import oracle.net.aso.p;
import survey.model.dao.SurveyDAO;
import survey.model.dto.SurveyDTO;

@WebServlet("/survey/*")
public class SurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String path = request.getContextPath(); //프로젝트 이름 을 뽑는다.
		String url = request.getRequestURL().toString(); //주소를뽑는다 
		String uri = request.getRequestURI().toString(); 
		
		String[] uriarray = uri.split("/");
		String urifilename = uriarray[uriarray.length-1];
		String urifilename2 = uriarray[uriarray.length-2];
		
		request.setAttribute("urifilename", urifilename);
		request.setAttribute("urifilename2", urifilename2);
		request.setAttribute("path", path);
		
		HttpSession session = request.getSession();
		String forward = "/WEB-INF/main/main.jsp";
		
		String searchOption = request.getParameter("searchOption");
		String searchData = request.getParameter("searchData");
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("searchData", searchData);
		
		//로그인 체크 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = (String)request.getSession().getAttribute("member_id");
		if(id == null || id.trim().equals("")) {
			out.println("<script language='javascript'>");
		    out.println("alert('로그인후 이용가능합니다.');");
		    out.println("window.location.href = '" + path + "/member/login';");
		    out.println("</script>");
		    out.flush();
		    return;
		}
//설문작성==================================================================================================
		if(urifilename.equals("insert")) {
			String member_level = (String)request.getSession().getAttribute("member_level");
			if(member_level == null || member_level.trim().equals("") || Integer.parseInt(member_level) < 1) {
				out.println("<script language='javascript'>");
			    out.println("alert('일반회원은 설문조사 작성이 불가능합니다.');");
			    out.println("window.location.href = '" + path + "';");
			    out.println("</script>");
			    out.flush();
			    return;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
		else if(urifilename.equals("insertProc")) {
			SurveyDTO dto = new SurveyDTO();
			SurveyDAO dao = new SurveyDAO();
			
			String survey_name = request.getParameter("survey_name");
			String survey_content = request.getParameter("survey_content");
			String survey_end = request.getParameter("survey_end");
			String survey_point = request.getParameter("survey_point");
			
			dto.setMember_id(id);
			dto.setSurvey_name(survey_name);
			dto.setSurvey_content(survey_content);
			dto.setSurvey_end(survey_end);
			dto.setSurvey_point(survey_point);
			int result = dao.insert(dto);
			dto.setSurvey_no(dao.survey_no());
			for(int i=1; i>0; i++) {
				if(request.getParameter("question_"+i) != null) {
					dto.setQuestion_no(request.getParameter("question_"+i));
					int result1 = dao.insertsurvey_question(dto);
					dto.setSurvey_question_no(dao.survey_question_no());
					
					String[] array = request.getParameterValues("option_"+i);
					for(int j=0; j<array.length; j++) {
						dto.setSurvey_answer_content(array[j]);
						int result2 = dao.insertsurvey_answer(dto);
					}
				}else {
					break;
				}
			}
			response.sendRedirect(path+"/survey/list");
		}
//설문 리스트=========================================================================================================
		else if(urifilename.equals("list")) {
			SurveyDAO dao = new SurveyDAO();
			
			String page_ = request.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int totalrecord = dao.total();
			int page = Integer.parseInt(page_);
			int blocksize = 6;
			int pagesize = 5;
			int maxpage = 0;
			int group = 0;
			int totalgroup = 0;
			int imsigroup = 0;
			
			if(totalrecord < blocksize) {
				maxpage = 1;
			}else if(totalrecord%blocksize !=0) {
				maxpage = (totalrecord/blocksize)+1;
			}else if (totalrecord%blocksize ==0) {
				maxpage = (totalrecord/blocksize);
			}
			
			if(pagesize > page) {
				group = 1;
			}else if(page%pagesize !=0) {
				group = (page/pagesize)+1;
			}else if (page%pagesize ==0) {
				group = (page/pagesize);
			}
			
			imsigroup = ((group-1) * pagesize)+1;
			
			if(maxpage%pagesize != 0) {
				totalgroup = (maxpage/pagesize)+1;
			}else {
				totalgroup = (maxpage/pagesize);
			}
			
			
			int firstrow = ((page-1) * blocksize)+1;
			int lastrow = (page * blocksize);
			
			
			List<SurveyDTO> list = dao.list(searchOption,searchData,firstrow,lastrow);
			SurveyDTO dto = new SurveyDTO();
			dto.setMember_id(id);
			List<SurveyDTO> list2 = dao.participationCompleted(dto);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			System.out.println(formatter.format(date));
			
			LocalDate now = LocalDate.now();
			
			request.setAttribute("now", now);
			request.setAttribute("imsigroup", imsigroup);
			request.setAttribute("totalgroup", totalgroup);
			request.setAttribute("group", group);
			request.setAttribute("page", page);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("list2size", list2.size());
			request.setAttribute("totalrecord", totalrecord);
			request.setAttribute("maxpage", maxpage);
			request.setAttribute("pagesize", pagesize);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);	
		}
//설문참여===========================================================================================
		else if(urifilename.equals("participation")) {
			int survey_no_ = Integer.parseInt(request.getParameter("survey_no"));
		
			SurveyDTO dto = new SurveyDTO();
			dto.setSurvey_no(survey_no_);
			SurveyDAO dao = new SurveyDAO();
			
			SurveyDTO surveydto = dao.selectsurvey(dto);
			List<SurveyDTO> list = dao.selectsurvey_question(dto);
			List<SurveyDTO> list1 = dao.selectsurvey_answer(dto);
			
			
			List<List<String>> list2 = new ArrayList<>();
			
			HashMap<String,Integer> map = new HashMap<>();
				
			int count = 0;
			for(int i=0; i<list.size(); i++) {
				List<String> imsilist = new ArrayList<>();
				for(int j=0; count<list1.size(); j++) {
					if(list.get(i).getSurvey_question_no() == list1.get(count).getSurvey_question_no()) {
						imsilist.add(list1.get(count).getSurvey_answer_content());
						map.put(count+"",list1.get(count).getSurvey_answer_no());
						count++;
					}else {
						request.setAttribute("count"+i, count);
						break;
					}
				}
				list2.add(imsilist);
			}
			
			request.setAttribute("survey_point", request.getParameter("survey_point"));
			request.setAttribute("map", map);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("dto", surveydto);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
		
		else if(urifilename.equals("participationProc")) {
			
			SurveyDTO dto = new SurveyDTO();
			dto.setMember_id(id);
			dto.setSurvey_point(request.getParameter("survey_point"));
			dto.setSurvey_no(Integer.parseInt(request.getParameter("survey_no")));
			SurveyDAO dao = new SurveyDAO();
			for(int i=0; i>=0; i++) {
				if(request.getParameter("문항_"+i) != null) {
					dto.setSurvey_answer_no(Integer.parseInt(request.getParameter("문항_"+i)));
					int result = dao.survey_user(dto);
				}else {
					break;
				}
			}
			int result = dao.insert_point(dto);
			
			if(result == 1) {
				out.println("<script language='javascript'>");
			    out.println("alert('설문에 참여해주셔셔 감사합니다./"+dto.getSurvey_point()+"POINT누적');");
			    out.println("window.location.href = '"+path+"/survey/list';");
			    out.println("</script>");
			    out.flush();
			    return;
			}else {
				out.println("<script language='javascript'>");
			    out.println("alert('오류발생');");
			    out.println("window.location.href = '"+path+"/survey/list';");
			    out.println("</script>");
			    out.flush();
			    return;
			}
			
		}
//설문 통계 (자기가 작성한 설문조사만 뜨도록)==================================================================================================		
		else if(urifilename.equals("statistics")) { 
			
			String member_level = (String)request.getSession().getAttribute("member_level");
			if(member_level == null || member_level.trim().equals("") || Integer.parseInt(member_level) < 1) {
				out.println("<script language='javascript'>");
			    out.println("alert('일반회원은 설문통계 보는것이 불가능 합니다.');");
			    out.println("window.location.href = '" + path + "';");
			    out.println("</script>");
			    out.flush();
			    return;
			}
			
			SurveyDTO dto = new SurveyDTO();
			dto.setMember_id(id);
			SurveyDAO dao = new SurveyDAO();
			
			String page_ = request.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int totalrecord = dao.total();
			int page = Integer.parseInt(page_);
			int blocksize = 3;
			int pagesize = 3;
			int maxpage = 0;
			int group = 0;
			int totalgroup = 0;
			int imsigroup = 0;
			
			if(totalrecord < blocksize) {
				maxpage = 1;
			}else if(totalrecord%blocksize !=0) {
				maxpage = (totalrecord/blocksize)+1;
			}else if (totalrecord%blocksize ==0) {
				maxpage = (totalrecord/blocksize);
			}
			
			if(pagesize > page) {
				group = 1;
			}else if(page%pagesize !=0) {
				group = (page/pagesize)+1;
			}else if (page%pagesize ==0) {
				group = (page/pagesize);
			}
			
			imsigroup = ((group-1) * pagesize)+1;
			
			if(maxpage%pagesize != 0) {
				totalgroup = (maxpage/pagesize)+1;
			}else {
				totalgroup = (maxpage/pagesize);
			}
			
			
			int firstrow = ((page-1) * blocksize)+1;
			int lastrow = (page * blocksize);
			
			List<SurveyDTO> list = dao.list_statistics(firstrow,lastrow,dto);
						
			request.setAttribute("imsigroup", imsigroup);
			request.setAttribute("totalgroup", totalgroup);
			request.setAttribute("group", group);
			request.setAttribute("page", page);
			request.setAttribute("list", list);
			request.setAttribute("totalrecord", totalrecord);
			request.setAttribute("maxpage", maxpage);
			request.setAttribute("pagesize", pagesize);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);	
		}
		
		else if(urifilename.equals("statisticsProc")) {
			SurveyDTO dto = new SurveyDTO();
			SurveyDAO dao = new SurveyDAO();
			int survey_no = Integer.parseInt(request.getParameter("survey_no"));
			dto.setSurvey_no(survey_no);
			
			int total_user = dao.total_survey_user(dto);
			Double total_user_= Double.valueOf(total_user);
			
			List<SurveyDTO> genderlist = dao.gender_count(dto);
			for(int i=0; i<2; i++) {
				if(genderlist.get(i).getMember_gender().equals("남자")) {
					request.setAttribute("mentotal", genderlist.get(i).getGender_count());
					request.setAttribute("mentotal2", Math.round((genderlist.get(i).getGender_count()/total_user_)*100));
				}else {
					request.setAttribute("womentotal", genderlist.get(i).getGender_count());
					request.setAttribute("womentotal2", Math.round((genderlist.get(i).getGender_count()/total_user_)*100));
				}
			}
			
			
			List<MemberDTO> agelist = dao.age_count(dto);
			
			for(int i=0; i<agelist.size(); i++) {
				agelist.get(i).setAge_count2(Math.round((agelist.get(i).getAge_count()/total_user_)*100));
			}
			
			
			
			SurveyDTO surveydto = dao.selectsurvey(dto);
			
			List<SurveyDTO> list = dao.selectsurvey_question(dto);
			List<SurveyDTO> list1 = dao.answer_count(dto);
			
			List<List<String>> list2 = new ArrayList<>();
			
			HashMap<String,Object> map = new HashMap<>();
			
			int count = 0;
			for(int i=0; i<list.size(); i++) {
				List<String> imsilist = new ArrayList<>();
				for(int j=0; count<list1.size(); j++) {
					if(list.get(i).getSurvey_question_no() == list1.get(count).getSurvey_question_no()) {
						imsilist.add(list1.get(count).getSurvey_answer_content());
						map.put(count+"", Math.round((list1.get(count).getAnswer_count()/total_user_)*100));
						count++;
					}else {
						request.setAttribute("count"+i, count);
						break;
					}
				}
				list2.add(imsilist);
			}
			request.setAttribute("agelist", agelist);
			request.setAttribute("agelist", agelist);
			request.setAttribute("total_user", total_user);
			request.setAttribute("map", map);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("dto", surveydto);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
//설문삭제 (DB에서 삭제는 X)=======================================================================================================================
		else if(urifilename.equals("surveydelete")) {
			int survey_no = Integer.parseInt(request.getParameter("survey_no"));
			SurveyDTO dto= new SurveyDTO();
			SurveyDAO dao = new SurveyDAO();
			dto.setSurvey_no(survey_no);
			
			int result =  dao.survey_delete(dto);
			if(result == 1) {
				out.println("<script language='javascript'>");
			    out.println("alert('삭제완료 되었습니다.');");
			    out.println("window.location.href = '" + path + "/survey/statistics';");
			    out.println("</script>");
			    out.flush();
			    return;
			}else {
				out.println("<script language='javascript'>");
			    out.println("alert('삭제실패!');");
			    out.println("window.location.href = '" + path + "/survey/statistics';");
			    out.println("</script>");
			    out.flush();
			    return;
			}			
			
		}
//1:1문의=======================================================================================================================================		
		else if(urifilename.equals("inquiry")) {		
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);	
		}
		else if(urifilename.equals("inquiryProc")) {
			SurveyDTO dto = new SurveyDTO();
			SurveyDAO dao = new SurveyDAO();
			String inquiry_subject = request.getParameter("inquiry_subject");
			String inquiry_type = request.getParameter("inquiry_type");
			String inquiry_content = request.getParameter("inquiry_content");
			String inquiry_disclosure = request.getParameter("inquiry_disclosure");
			
			if(inquiry_disclosure == null || inquiry_disclosure.trim().equals("")) {
				inquiry_disclosure = "공개";
			}
			
			String inquiry_complete = "접수";
			dto.setInquiry_disclosure(inquiry_disclosure);
			dto.setInquiry_subject(inquiry_subject);
			dto.setInquiry_type(inquiry_type);
			dto.setInquiry_content(inquiry_content);
			dto.setInquiry_complete(inquiry_complete);
			dto.setMember_id(id);
			
			int result = dao.insert_inquiry(dto);
			
			response.sendRedirect(path+"/survey/inquirylist");
		}
		else if(urifilename.equals("inquirylist")){
			SurveyDTO dto = new SurveyDTO();
			dto.setMember_id(id);
			
			SurveyDAO dao = new SurveyDAO();
			String page_ = request.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			
			int totalrecord = dao.total();
			int page = Integer.parseInt(page_);
			int blocksize = 3;
			int pagesize = 3;
			int maxpage = 0;
			int group = 0;
			int totalgroup = 0;
			int imsigroup = 0;
			
			if(totalrecord < blocksize) {
				maxpage = 1;
			}else if(totalrecord%blocksize !=0) {
				maxpage = (totalrecord/blocksize)+1;
			}else if (totalrecord%blocksize ==0) {
				maxpage = (totalrecord/blocksize);
			}
			
			if(pagesize > page) {
				group = 1;
			}else if(page%pagesize !=0) {
				group = (page/pagesize)+1;
			}else if (page%pagesize ==0) {
				group = (page/pagesize);
			}
			
			imsigroup = ((group-1) * pagesize)+1;
			
			if(maxpage%pagesize != 0) {
				totalgroup = (maxpage/pagesize)+1;
			}else {
				totalgroup = (maxpage/pagesize);
			}
			
			int firstrow = ((page-1) * blocksize)+1;
			int lastrow = (page * blocksize);
			
			List<SurveyDTO> list = dao.list_inquiry(firstrow,lastrow,dto);
			
			request.setAttribute("imsigroup", imsigroup);
			request.setAttribute("totalgroup", totalgroup);
			request.setAttribute("group", group);
			request.setAttribute("page", page);
			request.setAttribute("list", list);
			request.setAttribute("totalrecord", totalrecord);
			request.setAttribute("maxpage", maxpage);
			request.setAttribute("pagesize", pagesize);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);	
		}
//1:1문의 상세보기===========================================================================================================
		else if(urifilename.equals("inquiryselect")) {
			
			int inquiry_no = Integer.parseInt(request.getParameter("inquiry_no"));
			
			String member_name = request.getParameter("member_name");
			String member_level_ = (String)request.getSession().getAttribute("member_level");
			int member_level = Integer.parseInt(member_level_);
			
			SurveyDAO dao = new SurveyDAO();
			SurveyDTO dto = new SurveyDTO();
			dto.setInquiry_no(inquiry_no);
			dto = dao.inquiry_select(dto);
			
			if(dto.getInquiry_disclosure().equals("비공개")) {
				if(dto.getMember_id().equals(id) || member_level >= 2) {
					
				}else {
					out.println("<script language='javascript'>");
				    out.println("alert('비공개 글입니다.');");
				    out.println("window.location.href = '" + path + "/survey/inquirylist';");
				    out.println("</script>");
				    out.flush();
				    return;
				}
			}
			String inquiry_answer = dao.inquiry_answer(dto);
			dto.setInquiry_answer(inquiry_answer);
			dto.setMember_name(member_name);
			
			request.setAttribute("member_level", member_level);
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);	
		}
//1:1 문의 수정하기===========================================================================================================
		else if(urifilename.equals("inquiryupdate")) {
			int inquiry_no = Integer.parseInt(request.getParameter("inquiry_no")); 
			
			SurveyDAO dao = new SurveyDAO();
			SurveyDTO dto = new SurveyDTO();
			dto.setInquiry_no(inquiry_no);
			dto = dao.inquiry_select(dto);
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);	
			
		}
		else if(urifilename.equals("inquiryupdateProc")) {
			int inquiry_no = Integer.parseInt(request.getParameter("inquiry_no")); 
			String inquiry_disclosure = request.getParameter("inquiry_disclosure");
			String inquiry_type = request.getParameter("inquiry_type");
			String inquiry_subject = request.getParameter("inquiry_subject");
			String inquiry_content = request.getParameter("inquiry_content");
		
			if(inquiry_disclosure == null || inquiry_disclosure.trim().equals("")) {
				inquiry_disclosure = "공개";
			}
			SurveyDAO dao = new SurveyDAO();
			SurveyDTO dto = new SurveyDTO();
			
			dto.setInquiry_no(inquiry_no);
			dto.setInquiry_disclosure(inquiry_disclosure);
			dto.setInquiry_type(inquiry_type);
			dto.setInquiry_subject(inquiry_subject);
			dto.setInquiry_content(inquiry_content);
			
			int result = dao.inquiry_update(dto);
			if(result == 1) {
				out.println("<script language='javascript'>");
			    out.println("alert('수정완료 되었습니다.');");
			    out.println("window.location.href = '" + path + "/survey/inquirylist';");
			    out.println("</script>");
			    out.flush();
			    return;
			}else {
				out.println("<script language='javascript'>");
			    out.println("alert('수정실패!');");
			    out.println("window.location.href = '" + path + "/survey/inquirylist';");
			    out.println("</script>");
			    out.flush();
			    return;
			}			
		}
//1:1문의 삭제하기================================================================================================
		else if(urifilename.equals("inquirydelete")) {
			int inquiry_no = Integer.parseInt(request.getParameter("inquiry_no")); 		
			SurveyDAO dao = new SurveyDAO();
			SurveyDTO dto = new SurveyDTO();
			dto.setInquiry_no(inquiry_no);
			
			int result = dao.inquiry_delete(dto);
			
			if(result == 1) {
				out.println("<script language='javascript'>");
			    out.println("alert('글이 삭제되었습니다.');");
			    out.println("window.location.href = '" + path + "/survey/inquirylist';");
			    out.println("</script>");
			    out.flush();
			    return;
			}else {
				out.println("<script language='javascript'>");
			    out.println("alert('삭제실패!');");
			    out.println("window.location.href = '" + path + "/survey/inquirylist';");
			    out.println("</script>");
			    out.flush();
			    return;
			}			
		}
//1:1문의 답변=======================================================================================
		else if(urifilename.equals("inquiryanswer")) {
			int inquiry_no = Integer.parseInt(request.getParameter("inquiry_no"));
			String inquiry_answer = request.getParameter("inquiry_answer");
			
			System.out.println(inquiry_answer);
			
			SurveyDAO dao = new SurveyDAO();
			SurveyDTO dto = new SurveyDTO();
			dto.setMember_id(id);
			dto.setInquiry_no(inquiry_no);
			dto.setInquiry_answer(inquiry_answer);
			
			int result = dao.inquiry_answer_insert(dto);
			if(result == 1) {
				String inquiry_complete = "답변완료";
				dto.setInquiry_complete(inquiry_complete);
				dao.inquiry_complete_update(dto);
				System.out.println("답변 등록완료");
				response.sendRedirect(path);
			}else {
				System.out.println("답변 등록실패");
				response.sendRedirect(path);
			}
			
			
			
			
		}
		
		
	}
}
