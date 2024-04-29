package community.model.dto;

public class CommunityReplyDto {
//	REPLY_ID         NOT NULL NUMBER         
//	COMMUNITY_ID     NOT NULL NUMBER         
//	GAMER_ID         NOT NULL VARCHAR2(50)   
//	REPLY_CONTENT             VARCHAR2(4000) 
//	REPLY_WRITE_TIME NOT NULL TIMESTAMP(6)   
//	REPLY_LEVEL      NOT NULL NUMBER         
//	REPLY_REF                 NUMBER         
//	REPLY_STEP       NOT NULL NUMBER  
	private Integer replyId;
	private String communityId;
	private String gamerId;
	private String replyContent;
	private String replyWriteTime;
	private Integer replyLevel;
	private Integer replyRef;
	private Integer replyStep;
	@Override
	public String toString() {
		return "CommunityReplyDto [replyId=" + replyId + ", communityId=" + communityId + ", gamerId=" + gamerId
				+ ", replyContent=" + replyContent + ", replyWriteTime=" + replyWriteTime + ", replyLevel=" + replyLevel
				+ ", replyRef=" + replyRef + ", replyStep=" + replyStep + "]";
	}
	public CommunityReplyDto(Integer replyId, String communityId, String gamerId, String replyContent,
			String replyWriteTime, Integer replyLevel, Integer replyRef, Integer replyStep) {
		super();
		this.replyId = replyId;
		this.communityId = communityId;
		this.gamerId = gamerId;
		this.replyContent = replyContent;
		this.replyWriteTime = replyWriteTime;
		this.replyLevel = replyLevel;
		this.replyRef = replyRef;
		this.replyStep = replyStep;
	}
	public Integer getReplyId() {
		return replyId;
	}

	public String getCommunityId() {
		return communityId;
	}

	public String getGamerId() {
		return gamerId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public String getReplyWriteTime() {
		return replyWriteTime;
	}

	public Integer getReplyLevel() {
		return replyLevel;
	}

	public Integer getReplyRef() {
		return replyRef;
	}

	public Integer getReplyStep() {
		return replyStep;
	}

		
}
