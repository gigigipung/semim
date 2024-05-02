package shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.service.ShopService;

/**
 * Servlet implementation class ShopReadController
 */
@WebServlet("/shop/read")
public class ShopReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ShopService service = new ShopService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopReadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String gameIdStr = request.getParameter("id");
		String nationalCodeStr = request.getParameter("code");
		System.out.println("리드");
		System.out.println(gameIdStr);
		System.out.println(nationalCodeStr);
		try {
			int gameId = Integer.parseInt(gameIdStr);
			int nationalCode = Integer.parseInt(nationalCodeStr);
			request.setAttribute("dto", service.selectOne(gameId,nationalCode));
			System.out.println("나옴?");
			System.out.println(service.selectOne(gameId,nationalCode));
			request.getRequestDispatcher("/WEB-INF/shop/read.jsp").forward(request, response);
			
		}catch(NumberFormatException e) {
			System.out.println("!!! NumberFormatException !!!!!!");
			response.sendRedirect(request.getContextPath()+"/shop/list");
			
		}
	}
}
