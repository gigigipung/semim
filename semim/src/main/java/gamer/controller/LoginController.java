package gamer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gamer.model.dto.GamerInfoDto;
import gamer.model.dto.GamerLoginDto;
import gamer.model.service.GamerService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GamerService service = new GamerService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prePage = request.getParameter("prePage");
		request.getSession().setAttribute("prePage", prePage);
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		GamerLoginDto dto = new GamerLoginDto(id, pwd);
		System.out.println("/login dopost dto: "+dto);
		
		int result =0;
		
		GamerInfoDto resultInfo = service.loginGetInfo(dto);
		
		if(resultInfo != null) {
			request.getSession().setAttribute("sss", resultInfo);
			result = 1;
		}
		response.getWriter().append(String.valueOf(result));
		
	}

}
