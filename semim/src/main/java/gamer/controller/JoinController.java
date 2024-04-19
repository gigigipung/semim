package gamer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gamer.model.dto.GamerJoinDto;
import gamer.model.service.GamerService;

/**
 * Servlet implementation class JoinController
 */
@WebServlet("/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/join.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원가입 시도");
		String gamerId = request.getParameter("id");
		String gamerPwd = request.getParameter("pwd");
		String gamerEmail = request.getParameter("email");
		GamerJoinDto dto = new GamerJoinDto(gamerId,gamerPwd,gamerEmail);
		int result = new GamerService().insert(dto);
		if (result <= 0 ) {
			//TODO 미묘
			response.sendRedirect(request.getContextPath()+"/join");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
