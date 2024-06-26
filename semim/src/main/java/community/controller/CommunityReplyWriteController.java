package community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import community.model.dto.CommunityReplyListDto;
import community.model.dto.CommunityReplyWriteDto;
import community.model.service.CommunityService;
import gamer.model.dto.GamerInfoDto;

/**
 * Servlet implementation class CommunityReplyWriteController
 */
@WebServlet("/community/reply/write.ajax")
public class CommunityReplyWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService service = new CommunityService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityReplyWriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/community/reply/write.ajax doPost()");
		String communityReplyIdStr = request.getParameter("replyId");
		System.out.println("=====");
		System.out.println(communityReplyIdStr);
		int communityReplyId = 0;
		String communityIdStr = request.getParameter("communityId");
		System.out.println(communityIdStr);
		int boardId = 0;
		String replyContent = request.getParameter("replyContent");
		System.out.println(replyContent);
		// TODO LOGIN
		@SuppressWarnings("unchecked")
		GamerInfoDto loginInfo= (GamerInfoDto) request.getSession().getValue("sss");
		String gamerId = loginInfo.getGamerId();
		
		
		
		
		Gson gson = new Gson();
		
		if(communityIdStr == null || communityIdStr.equals("")) {
			response.getWriter().append("-1");
			return;
		}
		if(communityIdStr != null && !communityIdStr.equals("")) {
			try {
				boardId = Integer.parseInt(communityIdStr);
			}catch (NumberFormatException e) {
				response.getWriter().append("-1");
				return;
			}
		}
		if(communityReplyIdStr != null && !communityReplyIdStr.equals("")) {
			try {
				communityReplyId = Integer.parseInt(communityReplyIdStr);
			}catch (NumberFormatException e) {
				response.getWriter().append("-1");
				return;
			}
		}
		if(communityReplyIdStr == null || communityReplyIdStr.equals("")) {
			// 댓글 원본으로 간주함.
			communityReplyId = 0;
		}
		CommunityReplyWriteDto dto = new CommunityReplyWriteDto(communityReplyId, boardId, gamerId, replyContent);
		System.out.println(dto);
		int result = service.insertReply(dto);
//		response.getWriter().append(String.valueOf(result));
		if(result > 0) {
			List<CommunityReplyListDto> replydtolist = service.selectCommunityReplyList(boardId);
			response.getWriter().append(gson.toJson(replydtolist));
		
		}
	}
}
