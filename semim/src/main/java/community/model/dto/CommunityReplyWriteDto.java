package community.model.dto;

public class CommunityReplyWriteDto {
///REPLY_ID         NOT NULL NUMBER         
//COMMUNITY_ID     NOT NULL NUMBER         
//GAMER_ID         NOT NULL VARCHAR2(50)   
//REPLY_CONTENT             VARCHAR2(4000) 
//REPLY_WRITE_TIME NOT NULL TIMESTAMP(6)   
//REPLY_LEVEL      NOT NULL NUMBER         
//REPLY_REF                 NUMBER         
//REPLY_STEP       NOT NULL NUMBER 
	
	private Integer replyId;
	private Integer communityId;
	private String gamerId;
	private String replyContent;
	@Override
	public String toString() {
		return "CommunityReplyWriteDto [replyId=" + replyId + ", communityId=" + communityId + ", gamerId=" + gamerId
				+ ", replyContent=" + replyContent + "]";
	}
	public CommunityReplyWriteDto(Integer replyId, Integer communityId, String gamerId, String replyContent) {
		super();
		this.replyId = replyId;
		this.communityId = communityId;
		this.gamerId = gamerId;
		this.replyContent = replyContent;
	}
	public Integer getReplyId() {
		return replyId;
	}

	public Integer getCommunityId() {
		return communityId;
	}

	public String getGamerId() {
		return gamerId;
	}

	public String getReplyContent() {
		return replyContent;
	}

}
