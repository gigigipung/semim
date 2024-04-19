package gamer.model.dto;

public class GamerJoinDto {
//	GAMER_ID     NOT NULL VARCHAR2(50) 
//	GAMER_PWD    NOT NULL VARCHAR2(20) 
//	GAMER_ EMAIL NOT NULL VARCHAR2(50) 
//	GAMER_USE             NUMBER       
//	Field                 NUMBER  
	private String gamerId;
	private String gamerPwd;
	private String gamerEmail;
	@Override
	public String toString() {
		return "GamerJoinDto [gamerId=" + gamerId + ", gamerPwd=" + gamerPwd + ", gamerEmail=" + gamerEmail + "]";
	}
	public GamerJoinDto(String gamerId, String gamerPwd, String gamerEmail) {
		super();
		this.gamerId = gamerId;
		this.gamerPwd = gamerPwd;
		this.gamerEmail = gamerEmail;
	}
	public String getGamerId() {
		return gamerId;
	}

	public String getGamerPwd() {
		return gamerPwd;
	}

	public String getGamerEmail() {
		return gamerEmail;
	}

}
