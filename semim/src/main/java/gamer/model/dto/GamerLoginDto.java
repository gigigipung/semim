package gamer.model.dto;

public class GamerLoginDto {
//	GAMER_ID     NOT NULL VARCHAR2(50) 
//	GAMER_PWD    NOT NULL VARCHAR2(20) 
//	GAMER_ EMAIL NOT NULL VARCHAR2(50) 
//	GAMER_USE             NUMBER       
//	Field                 NUMBER  
	private String gamerId;
	private String gamerPwd;
	@Override
	public String toString() {
		return "GamerLoginDto [gamerId=" + gamerId + ", gamerPwd=" + gamerPwd + "]";
	}
	public GamerLoginDto(String gamerId, String gamerPwd) {
		super();
		this.gamerId = gamerId;
		this.gamerPwd = gamerPwd;
	}
	public String getGamerId() {
		return gamerId;
	}
	public String getGamerPwd() {
		return gamerPwd;
	}
}
