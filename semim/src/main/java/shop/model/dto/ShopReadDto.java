package shop.model.dto;

public class ShopReadDto {
//	NATIONAL_CODE NOT NULL NUMBER         
//	GAME_ID       NOT NULL NUMBER         
//	GAME_IMG               VARCHAR2(500)  
//	GAME_NAME     NOT NULL VARCHAR2(50)   
//	GAME_PRICE    NOT NULL NUMBER         
//	GAME_CONTENT           VARCHAR2(4000)
	private Integer nationalCode;
	private Integer gameID;
	private String gameImg;
	private String gameName;
	private String gamePrice;
	private String gameOpen;
	private String gameContent;
	@Override
	public String toString() {
		return "ShopReadDto [nationalCode=" + nationalCode + ", gameID=" + gameID + ", gameImg=" + gameImg
				+ ", gameName=" + gameName + ", gamePrice=" + gamePrice + ", gameOpen=" + gameOpen + ", gameContent="
				+ gameContent + "]";
	}
	public ShopReadDto(Integer nationalCode, Integer gameID, String gameImg, String gameName, String gamePrice,
			String gameOpen, String gameContent) {
		super();
		this.nationalCode = nationalCode;
		this.gameID = gameID;
		this.gameImg = gameImg;
		this.gameName = gameName;
		this.gamePrice = gamePrice;
		this.gameOpen = gameOpen;
		this.gameContent = gameContent;
	}
	public Integer getNationalCode() {
		return nationalCode;
	}

	public Integer getGameID() {
		return gameID;
	}

	public String getGameImg() {
		return gameImg;
	}

	public String getGameName() {
		return gameName;
	}

	public String getGamePrice() {
		return gamePrice;
	}

	public String getGameOpen() {
		return gameOpen;
	}

	public String getGameContent() {
		return gameContent;
	}
	
}
