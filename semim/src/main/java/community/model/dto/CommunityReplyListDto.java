package community.model.dto;

public class CommunityReplyListDto {
//	REPLY_ID         NOT NULL NUMBER         
//	COMMUNITY_ID     NOT NULL NUMBER         
//	GAMER_ID         NOT NULL VARCHAR2(50)   
//	REPLY_CONTENT             VARCHAR2(4000) 
//	REPLY_WRITE_TIME NOT NULL TIMESTAMP(6)   
//	REPLY_LEVEL      NOT NULL NUMBER         
//	REPLY_REF                 NUMBER         
//	REPLY_STEP       NOT NULL NUMBER  
	private Integer replyId;
	private String gamerId;
	private String replyContent;
	private String replyWriteTime;
	private Integer replyLevel;
	private Integer replyRef;
	private Integer replyStep;
	@Override
	public String toString() {
		return "CommunityReplyListDto [replyId=" + replyId + ", gamerId=" + gamerId + ", replyContent=" + replyContent
				+ ", replyWriteTime=" + replyWriteTime + ", replyLevel=" + replyLevel + ", replyRef=" + replyRef
				+ ", replyStep=" + replyStep + "]";
	}
	public CommunityReplyListDto(Integer replyId, String gamerId, String replyContent, String replyWriteTime,
			Integer replyLevel, Integer replyRef, Integer replyStep) {
		super();
		this.replyId = replyId;
		this.gamerId = gamerId;
		this.replyContent = replyContent;
		this.replyWriteTime = replyWriteTime;
		this.replyLevel = replyLevel;
		this.replyRef = replyRef;
		this.replyStep = replyStep;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public void setGamerId(String gamerId) {
		this.gamerId = gamerId;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setReplyWriteTime(String replyWriteTime) {
		this.replyWriteTime = replyWriteTime;
	}

	public void setReplyLevel(Integer replyLevel) {
		this.replyLevel = replyLevel;
	}

	public void setReplyRef(Integer replyRef) {
		this.replyRef = replyRef;
	}

	public void setReplyStep(Integer replyStep) {
		this.replyStep = replyStep;
	}

	
	
}
