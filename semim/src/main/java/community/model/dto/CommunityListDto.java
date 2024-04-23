package community.model.dto;

public class CommunityListDto {
//	COMMUNITY_ID         NOT NULL NUMBER         
//	COMMUNITY_NAME       NOT NULL VARCHAR2(20)   
//	COMMUNITY_CONTENT             VARCHAR2(1000) 
//	COMMUNITY_WRITE_TIME NOT NULL TIMESTAMP(6)   
//	GAMER_ID             NOT NULL VARCHAR2(50)   
//	READ_COUNT                    NUMBER   
	private Integer communityId;
	private String communityName;
	private String communityWriteTime;
	private String gamerId;
	private Integer readCount;
	@Override
	public String toString() {
		return "CommunityListDto [communityId=" + communityId + ", communityName=" + communityName
				+ ", communityWriteTime=" + communityWriteTime + ", gamerId=" + gamerId + ", readCount=" + readCount
				+ "]";
	}
	public CommunityListDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommunityListDto(Integer communityId, String communityName, String communityWriteTime, String gamerId,
			Integer readCount) {
		super();
		this.communityId = communityId;
		this.communityName = communityName;
		this.communityWriteTime = communityWriteTime;
		this.gamerId = gamerId;
		this.readCount = readCount;
	}
	public Integer getCommunityId() {
		return communityId;
	}

	public String getCommunityName() {
		return communityName;
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

	
}