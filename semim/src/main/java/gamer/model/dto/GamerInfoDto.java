package gamer.model.dto;

public class GamerInfoDto {
//	GAMER_ID     NOT NULL VARCHAR2(50) 
//	GAMER_PWD    NOT NULL VARCHAR2(20) 
//	GAMER_ EMAIL NOT NULL VARCHAR2(50) 
//	GAMER_USE             NUMBER       
//	Field                 NUMBER 
	private String GamerId;
	private String GamerEmail;
	@Override
	public String toString() {
		return "GamerInfoDto [GamerId=" + GamerId + ", GamerEmail=" + GamerEmail + "]";
	}
	public GamerInfoDto(String gamerId, String gamerEmail) {
		super();
		GamerId = gamerId;
		GamerEmail = gamerEmail;
	}
	public String getGamerId() {
		return GamerId;
	}
	public String getGamerEmail() {
		return GamerEmail;
	}
}
