package shop.model.dto;

public class ShopListDto {
//	GAME_ID     NOT NULL NUMBER        
//	GAME_SERVER NOT NULL VARCHAR2(500) 
//	GAME_OPEN            DATE          

	
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
	private Integer gamePrice;
	private String gameOpen;
	@Override
	public String toString() {
		return "ShopListDto [nationalCode=" + nationalCode + ", gameID=" + gameID + ", gameImg=" + gameImg
				+ ", gameName=" + gameName + ", gamePrice=" + gamePrice + ", gameOpen=" + gameOpen + "]";
	}
	public ShopListDto(Integer nationalCode, Integer gameID, String gameImg, String gameName, Integer gamePrice,
			String gameOpen) {
		super();
		this.nationalCode = nationalCode;
		this.gameID = gameID;
		this.gameImg = gameImg;
		this.gameName = gameName;
		this.gamePrice = gamePrice;
		this.gameOpen = gameOpen;
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

	public Integer getGamePrice() {
		return gamePrice;
	}

	public String getGameOpen() {
		return gameOpen;
	}

	



	
}
