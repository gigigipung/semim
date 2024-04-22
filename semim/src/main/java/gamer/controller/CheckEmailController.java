package gamer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gamer.model.service.GamerService;

/**
 * Servlet implementation class CheckEmailController
 */
@WebServlet("/checkemail.ajax")
public class CheckEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이메일 중복확인");
		System.out.println(request.getParameter("email"));
		
		String email = request.getParameter("email");
		System.out.println(email);
		int result = new GamerService().selectCheckId(email);
		response.getWriter().append(String.valueOf(result));
	}

}
