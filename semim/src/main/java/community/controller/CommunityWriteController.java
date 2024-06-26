package community.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import community.model.dto.CommunityInsertDto;
import community.model.dto.FileWriterDto;
import community.model.service.CommunityService;
import gamer.model.dto.GamerInfoDto;

/**
 * Servlet implementation class CommunityWriteController
 */
@WebServlet("/community/write")
public class CommunityWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommunityService service = new CommunityService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityWriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String prePage = (String)request.getSession().getAttribute("prePage");
		if(prePage != null && prePage.equals("write")) {
			request.getSession().removeAttribute("prePage");
		}
		request.getRequestDispatcher("/WEB-INF/community/write.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/board/write doPost()");
		
		String uploadPath = request.getServletContext().getRealPath("files");
		System.out.println("uploadPath:"+uploadPath);
		File uploadPathFile = new File(uploadPath);
		if(!uploadPathFile.exists()) {
			uploadPathFile.mkdirs();
		}
		int uploadFileLimit = 10 * 1024 *1024; // 10M제한
		
		MultipartRequest multiReq = new MultipartRequest(request,  // jsp->controll로 전달된 객체 
				uploadPath,  //  서버에 저장할 디렉토리 
				uploadFileLimit, // 업로드 파일 크기제한
				"UTF-8", // 인코딩 방법
				new DefaultFileRenamePolicy() // was 서버에 저장할 디렉토리에 동일이름이 존재할때 새로운 이름 부여방식 
				);

		List<FileWriterDto> fileList = new ArrayList<FileWriterDto>();

		Enumeration<?> fileNames = multiReq.getFileNames();
		while(fileNames.hasMoreElements()) {
			String name = (String)fileNames.nextElement();   // input type="file" name="xxx", xxx_0, xxx_1
			String fileName = multiReq.getFilesystemName(name);  // 서버에 저장된 파일이름
			String orginFileName = multiReq.getOriginalFileName(name);
			String type = multiReq.getContentType(name);  // 전송된 파일의 타입
			File f1= multiReq.getFile(name);  // name을 이용해서 파일 객체 생성 여부 확인 작업.
			if (f1==null) {  // name을 이용해서 파일 객체 생성에 실패하면
				System.out.println("파일 업로드 실패");   // 실패 오류메시지  
			} else {
			}
			FileWriterDto filedto = new FileWriterDto(fileName, orginFileName);
			fileList.add(filedto);			
		}
		
		String communityName = multiReq.getParameter("subject");
		String communityContent = multiReq.getParameter("content");
		
		GamerInfoDto gamerInfoDto = (GamerInfoDto)request.getSession().getAttribute("sss");
		String gamerId = gamerInfoDto.getGamerId();
		CommunityInsertDto dto = new CommunityInsertDto(communityName, communityContent, gamerId, fileList);  
		System.out.println(dto);
		int result = service.insert(dto);
		response.sendRedirect(request.getContextPath()+"/community/list");
		
	}

}