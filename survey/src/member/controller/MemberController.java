package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.bouncycastle.asn1.its.SequenceOfPsidGroupPermissions;

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import survey.model.dao.SurveyDAO;
import survey.model.dto.SurveyDTO;
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
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
		
		//로그인 확인 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = (String)request.getSession().getAttribute("member_id");
		if(!urifilename.equals("insert") && !urifilename.equals("insertProc") && !urifilename.equals("login") && !urifilename.equals("loginProc") && !urifilename.equals("insertchoose") && !urifilename.equals("idcheckProc")) {
			if(id == null || id.trim().equals("")) {
				out.println("<script language='javascript'>");
			    out.println("alert('로그인후 이용가능합니다.');");
			    out.println("window.location.href = '" + path + "/member/login';");
			    out.println("</script>");
			    out.flush();
			    return;
			}
		}
		String forward = "/WEB-INF/main/main.jsp";
//목록 ================================================================================================================
		if(urifilename.equals("list")) {
			
			String member_level = (String)request.getSession().getAttribute("member_level");
			if(member_level == null || member_level.trim().equals("") || Integer.parseInt(member_level) < 3) {
				out.println("<script language='javascript'>");
			    out.println("alert('관리자 페이지입니다.');");
			    out.println("window.location.href = '" + path + "';");
			    out.println("</script>");
			    out.flush();
			    return;
			}
			
			String page_ = request.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			MemberDAO dao = new MemberDAO();
			int totalrecord = dao.total();
			int page = Integer.parseInt(page_);
			int blocksize = 3;
			int pagesize = 4;
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
			
			List<MemberDTO> list = dao.list(firstrow,lastrow);
			
			System.out.println("totalgroup: "+totalgroup);
			System.out.println("totalrecord: "+totalrecord);
			System.out.println("maxpage: "+maxpage);
			System.out.println("pagesize: "+pagesize );
			System.out.println("group: "+group);
			System.out.println("page: "+page);
			
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
//회원등록===================================================================================================================
		else if(urifilename.equals("insertchoose")) {
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
		else if(urifilename.equals("insert")) {
			if(request.getParameter("code") != null) {
				request.setAttribute("code", request.getParameter("code"));
			}
			System.out.println("code:"+request.getParameter("code"));
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
		
		else if(urifilename.equals("insertProc")) {
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			LocalDate now = LocalDate.now();
			int year = now.getYear();
			int month = now.getMonthValue();
			int day = now.getDayOfMonth();
			 
			String member_id = request.getParameter("member_id");
			String member_password = request.getParameter("member_password");
			String member_name = request.getParameter("member_name");
			String member_birth = request.getParameter("member_birth");
			String member_ssn = request.getParameter("member_ssn");
			String member_phone1 = request.getParameter("member_phone1");
			String member_phone2 = request.getParameter("member_phone2");
			String member_phone3 = request.getParameter("member_phone3");
			String member_addr1 = request.getParameter("member_addr1");
			String member_addr2 = request.getParameter("member_addr2");
			String member_addr3 = request.getParameter("member_addr3");
			String member_addr4 = request.getParameter("member_addr4");
			int member_age = 0;
			String member_gender ="남자";
			String member_level = request.getParameter("member_level");
			String member_point = "0";
			String business_number = request.getParameter("business_number");
			
			if(member_level == null || member_level.trim().equals("")) {
				member_level = "0";
			}
			if(business_number == null || business_number.trim().equals("")) {
				business_number = "-";
			}
			
			
			
			if(member_ssn.equals("1") || member_ssn.equals("2")) {
				
				if(Integer.parseInt(member_birth.substring(2,4)) <= month ) {
					member_age = year - Integer.parseInt(19+member_birth.substring(0,2));
				}else {
					member_age = (year-1) - Integer.parseInt(19+member_birth.substring(0,2));
				}
				if(member_ssn.equals("2")) {
					member_gender = "여자";
				}
			}
			if(member_ssn.equals("3") || member_ssn.equals("4")) {
				if(Integer.parseInt(member_birth.substring(2,4)) <= month ) {
					member_age = year - Integer.parseInt(20+member_birth.substring(0,2));
				}else {
					member_age = (year-1) - Integer.parseInt(20+member_birth.substring(0,2));
				}
				if(member_ssn.equals("4")) {
					member_gender = "여자";
				}
			}
			
			dto.setBusiness_number(business_number);
			dto.setMember_addr1(member_addr1);
			dto.setMember_addr2(member_addr2);
			dto.setMember_addr3(member_addr3);
			dto.setMember_addr4(member_addr4);
			dto.setMember_age(member_age);
			dto.setMember_birth(member_birth);
			dto.setMember_gender(member_gender);
			dto.setMember_id(member_id);
			dto.setMember_level(member_level);
			dto.setMember_name(member_name);
			dto.setMember_password(member_password);
			dto.setMember_phone1(member_phone1);
			dto.setMember_phone2(member_phone2);
			dto.setMember_phone3(member_phone3);
			dto.setMember_point(member_point);
			dto.setMember_ssn(member_ssn);
			
			int result = dao.insert(dto);
			
			if(result == 1) {
				System.out.println("회원가입 완료");
			}else {
				System.out.println("회원가입 실패 ");
			}
			response.sendRedirect(path+"/member/list");
		}
//회원가입 ID체크=======================================================================================
	
	
//회원마이페이지=========================================================================================
		else if(urifilename.equals("select")) {
			MemberDTO dto = new MemberDTO();
			dto.setMember_id(id);	
			MemberDAO dao = new MemberDAO();
			dto = dao.select(dto);
			
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
			
		}
//회원 정보수정==========================================================================================
		else if(urifilename.equals("update")) {
			MemberDTO dto = new MemberDTO();
			dto.setMember_id(id);	
			MemberDAO dao = new MemberDAO();
			dto = dao.select(dto);
			
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
		else if(urifilename.equals("updateProc")) {
			MemberDTO dto = new MemberDTO();
			dto.setMember_id(id);	
			MemberDAO dao = new MemberDAO();
			
			String member_name = request.getParameter("member_name");
			String member_phone1 = request.getParameter("member_phone1");
			String member_phone2 = request.getParameter("member_phone2");
			String member_phone3 = request.getParameter("member_phone3");
			String member_addr1 = request.getParameter("member_addr1");
			String member_addr2 = request.getParameter("member_addr2");
			String member_addr3 = request.getParameter("member_addr3");
			String member_addr4 = request.getParameter("member_addr4");
			
			dto.setMember_addr1(member_addr1);
			dto.setMember_addr2(member_addr2);
			dto.setMember_addr3(member_addr3);
			dto.setMember_addr4(member_addr4);
			dto.setMember_name(member_name);
			dto.setMember_phone1(member_phone1);
			dto.setMember_phone2(member_phone2);
			dto.setMember_phone3(member_phone3);
			
			
			request.setAttribute("dto", dto);
			
			int result = dao.update(dto);
			
			if(result == 1) {
				System.out.println("수정 완료");
				response.sendRedirect(path+"/member/select");
			}else {
				System.out.println("수정 실패 ");
				response.sendRedirect(path+"/member/update");
			}
		}
		
//회원탈퇴==============================================================================================
		else if (urifilename.equals("delete")) {
			MemberDTO dto = new MemberDTO();
			dto.setMember_id(id);	
			MemberDAO dao = new MemberDAO();
			dto = dao.select(dto);
			
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
		else if (urifilename.equals("deleteProc")) {
			MemberDTO dto = new MemberDTO();
			dto.setMember_id(id);	
			MemberDAO dao = new MemberDAO();
			int result = dao.delete(dto);
			
			if(result == 1) {
				System.out.println("삭제 완료");
				response.sendRedirect(path+"/member/logout");
			}else {
				System.out.println("삭제 실패 ");
				response.sendRedirect(path+"/member/delete");
			}
		}
		
		
		
//로그인================================================================================================		
		else if(urifilename.equals("login")) {
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
		else if(urifilename.equals("loginProc")) {
			MemberDTO dto = new MemberDTO();
			String member_id = request.getParameter("member_id");
			String member_password = request.getParameter("member_password");
			dto.setMember_id(member_id);
			dto.setMember_password(member_password);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO resultdto = dao.login(dto);
			
			
			if(resultdto.getMember_id() == null || resultdto.getMember_id().equals("")) {
				out.println("<script language='javascript'>");
			    out.println("alert('아이디 또는 비밀번호를 잘못 입력하셨습니다.');");
			    out.println("window.location.href = '" + path + "/member/login';");
			    out.println("</script>");
			    out.flush();
			    return;
			}else {
				request.getSession().setAttribute("member_id", resultdto.getMember_id());
				request.getSession().setAttribute("member_password", resultdto.getMember_password());
				request.getSession().setAttribute("member_name", resultdto.getMember_name());
				request.getSession().setAttribute("member_level", resultdto.getMember_level());
				
				request.setAttribute("urifilename2", null);
				RequestDispatcher rd = request.getRequestDispatcher(forward);
				rd.forward(request, response);
			}
		}
		
		else if(urifilename.equals("logout")) {
			session = request.getSession(false);	
			session.invalidate(); 
			request.setAttribute("urifilename2", null);
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
//설문조사 참여 내역======================================================================================
		else if(urifilename.equals("history")) { 
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
			
			List<SurveyDTO> list = dao.history(firstrow,lastrow,dto);
						
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
//비밀번호 변경=============================================================================================
		else if(urifilename.equals("passwordupdate")) {
			if(id == null || id.trim().equals("")) {
				response.sendRedirect(path);
				return;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
	
		else if(urifilename.equals("passwordupdateProc")) {
			if(id == null || id.trim().equals("")) {
				response.sendRedirect(path);
				return;
			}
			
			
			MemberDTO dto = new MemberDTO();
			dto.setMember_id(id);	
			MemberDAO dao = new MemberDAO();
			String password = request.getParameter("password");
			dto.setMember_password(password);
			
			int result = dao.passwordupdate(dto);
			
			if(result == 1) {
				System.out.println("비밀번호 변경 완료");
				response.sendRedirect(path+"/member/select");
			}else {
				System.out.println("비밀번호 변경 실패 ");
				response.sendRedirect(path+"/member/passwordupdate");
			}
			
		}
//회원 포인트 적립 조회=======================================================================================
		else if(urifilename.equals("accumulate")) {
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
			
			List<SurveyDTO> list = dao.accumulate(firstrow,lastrow,dto);
			
			int sum = 0;
			for(int i=0; i<list.size(); i++) {
				sum+= list.get(i).getSurvey_point_getpoint();
			}
			
			request.setAttribute("sumpoint", sum);
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
		
		
		
		
		
		
	}
}
