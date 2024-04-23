package community.model.dto;

public class FileReadDto {
//	FILE_ID       NOT NULL NUMBER         
//	COMMUNITY_ID  NOT NULL NUMBER         
//	FILE_PATH     NOT NULL VARCHAR2(1000) 
//	FILE_ORIGINAL NOT NULL VARCHAR2(300)
	private Integer fileId;
	private Integer communityId;
	private String filePath;
	private String fileOriginal;
	@Override
	public String toString() {
		return "FileReadDto [fileId=" + fileId + ", communityId=" + communityId + ", filePath=" + filePath
				+ ", fileOriginal=" + fileOriginal + "]";
	}
	public FileReadDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getFileId() {
		return fileId;
	}

	public Integer getCommunityId() {
		return communityId;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileOriginal() {
		return fileOriginal;
	}

	public FileReadDto(Integer fileId, Integer communityId, String filePath, String fileOriginal) {
		super();
		this.fileId = fileId;
		this.communityId = communityId;
		this.filePath = filePath;
		this.fileOriginal = fileOriginal;
	}
}
