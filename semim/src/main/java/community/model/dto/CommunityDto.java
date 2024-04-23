package community.model.dto;

public class CommunityDto {
//	COMMUNITY_ID         NOT NULL NUMBER         
//	COMMUNITY_NAME       NOT NULL VARCHAR2(20)   
//	COMMUNITY_CONTENT             VARCHAR2(1000) 
//	COMMUNITY_WRITE_TIME NOT NULL TIMESTAMP(6)   
//	GAMER_ID             NOT NULL VARCHAR2(50)   
//	READ_COUNT                    NUMBER   
	private Integer communityId;
	private String communityName;
	private String communityContemt;
	private String communityWriteTime;
	private String gamerId;
	private Integer readCount;
	@Override
	public String toString() {
		return "CommunityDto [communityId=" + communityId + ", communityName=" + communityName + ", communityContemt="
				+ communityContemt + ", communityWriteTime=" + communityWriteTime + ", gamerId=" + gamerId
				+ ", readCount=" + readCount + "]";
	}
	public CommunityDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommunityDto(Integer communityId, String communityName, String communityContemt, String communityWriteTime,
			String gamerId, Integer readCount) {
		super();
		this.communityId = communityId;
		this.communityName = communityName;
		this.communityContemt = communityContemt;
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
	public String getCommunityContemt() {
		return communityContemt;
	}
	public void setCommunityContemt(String communityContemt) {
		this.communityContemt = communityContemt;
	}
	public String getCommunityWriteTime() {
		return communityWriteTime;
	}
	public void setCommunityWriteTime(String communityWriteTime) {
		this.communityWriteTime = communityWriteTime;
	}
	public String getGamerId() {
		return gamerId;
	}
	public void setGamerId(String gamerId) {
		this.gamerId = gamerId;
	}
	public Integer getReadCount() {
		return readCount;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
}
