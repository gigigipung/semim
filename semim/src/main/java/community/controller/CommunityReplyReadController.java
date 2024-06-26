package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import community.model.service.CommunityService;

/**
 * Servlet implementation class CommunityReplyReadController
 */
@WebServlet("/community/reply/read.ajax")
public class CommunityReplyReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService service = new CommunityService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityReplyReadController() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String communityIdStr = request.getParameter("communityId");
		try {
			int communityId = Integer.parseInt(communityIdStr);
			response.getWriter().append(new Gson().toJson(service.selectCommunityReplyList(communityId)));
		} catch (Exception e) {
			System.out.println("!!! NumberFormatException !!!!!!");
			response.sendRedirect(request.getContextPath()+"/community/list");
		}
	}

}
