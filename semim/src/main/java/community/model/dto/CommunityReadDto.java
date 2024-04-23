package community.model.dto;

import java.util.List;

public class CommunityReadDto {
//	COMMUNITY_ID         NOT NULL NUMBER         
//	COMMUNITY_NAME       NOT NULL VARCHAR2(20)   
//	COMMUNITY_CONTENT             VARCHAR2(1000) 
//	COMMUNITY_WRITE_TIME NOT NULL TIMESTAMP(6)   
//	GAMER_ID             NOT NULL VARCHAR2(50)   
//	READ_COUNT                    NUMBER   
	private Integer communityId;
	private String communityName;
	private String communityContent;
	private String communityWriteTime;
	private String gamerId;
	private Integer readCount;
	
	private List<FileReadDto> filedtolist;

	@Override
	public String toString() {
		return "CommunityReadDto [communityId=" + communityId + ", communityName=" + communityName
				+ ", communityContent=" + communityContent + ", communityWriteTime=" + communityWriteTime + ", gamerId="
				+ gamerId + ", readCount=" + readCount + ", filedtolist=" + filedtolist + "]";
	}

	public CommunityReadDto(Integer communityId, String communityName, String communityContent,
			String communityWriteTime, String gamerId, Integer readCount) {
		super();
		this.communityId = communityId;
		this.communityName = communityName;
		communityContent = communityContent.replaceAll("\\r?\\n", "<br>");
		communityContent = communityContent.replaceAll(" ", "&nbsp;");
		this.communityContent = communityContent;
		this.communityWriteTime = communityWriteTime;
		this.gamerId = gamerId;
		this.readCount = readCount;
	}

	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCommunityContent() {
		return communityContent;
	}

	public String getCommunityWriteTime() {
		return communityWriteTime;
	}

	public String getGamerId() {
		return gamerId;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public List<FileReadDto> getFiledtolist() {
		return filedtolist;
	}

	public void setFiledtolist(List<FileReadDto> filedtolist) {
		this.filedtolist = filedtolist;
	}
}
