package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommunityService;

/**
 * Servlet implementation class CommunityListController
 */
@WebServlet("/community/list")
public class CommunityListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommunityService service =new CommunityService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageSize= 3;
		int pageBlockSize = 5;
		int currentPageNum = 1;
		
		String pageNum = request.getParameter("page");
		if(pageNum != null && !pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (Exception e) {
				 System.out.println("numbetformat");
			}
		}
		request.setAttribute("map" , service.selectPageList(pageSize, pageBlockSize, currentPageNum));
//		request.setAttribute("dtolist", service.selectAllList());
		request.getRequestDispatcher("/WEB-INF/community/list.jsp").forward(request, response);
	}
}

