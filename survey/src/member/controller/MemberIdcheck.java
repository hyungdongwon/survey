package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;

@WebServlet("/memberidcheck/*")
public class MemberIdcheck extends HttpServlet {
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
		
		System.out.println("idcheckProc");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(urifilename.equals("idcheckProc")) {
			String member_id = request.getParameter("member_id");
			MemberDTO dto =new MemberDTO();
			MemberDAO dao = new MemberDAO();
			dto.setMember_id(member_id);
			
			String data = dao.idcheck(dto);
			
			if(data != null) {
				data="";
			}
			out.print(data);
		}
		
		
	}
}
