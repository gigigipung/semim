package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommunityService;

/**
 * Servlet implementation class CommunityReadController
 */
@WebServlet("/community/read")
public class CommunityReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService service = new CommunityService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityReadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String CommunityIdStr = request.getParameter("id");
		try {
			int CommunityId = Integer.parseInt(CommunityIdStr);
			request.setAttribute("dto", service.selectOne(CommunityId));
			request.getRequestDispatcher("/WEB-INF/community/read.jsp").forward(request, response);
			
		}catch(NumberFormatException e) {
			System.out.println("!!! NumberFormatException !!!!!!");
			response.sendRedirect(request.getContextPath()+"community/list");
		}
	}

}
