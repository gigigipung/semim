package community.model.dto;

import java.util.List;

public class CommunityInsertDto {
//	COMMUNITY_ID         NOT NULL NUMBER         
//	COMMUNITY_NAME       NOT NULL VARCHAR2(20)   
//	COMMUNITY_CONTENT             VARCHAR2(1000) 
//	COMMUNITY_WRITE_TIME NOT NULL TIMESTAMP(6)   
//	GAMER_ID             NOT NULL VARCHAR2(50)   
//	READ_COUNT                    NUMBER   
	private String communityName;
	private String communityContent;
	private String gamerId;
	private List<FileWriterDto> fileList;
	@Override
	public String toString() {
		return "CommunityInsertDto [communityName=" + communityName + ", communityContent=" + communityContent
				+  ", gamerId=" + gamerId + ", fileList=" + fileList
				+ "]";
	}
	public CommunityInsertDto(String communityName, String communityContent, String gamerId,
			List<FileWriterDto> fileList) {
		super();
		this.communityName = communityName;
		this.communityContent = communityContent;
		this.gamerId = gamerId;
		this.fileList = fileList;
	}
	public String getCommunityName() {
		return communityName;
	}
	public String getCommunityContent() {
		return communityContent;
	}
	public String getGamerId() {
		return gamerId;
	}
	public List<FileWriterDto> getFileList() {
		return fileList;
	}
	
	
}
