package community.model.dto;

public class FileWriterDto {
	private String filePath;
	private String fileOriginal;
	@Override
	public String toString() {
		return "FileWriterDto [filePath=" + filePath + ", fileOriginal=" + fileOriginal + "]";
	}
	public FileWriterDto(String filePath, String fileOriginal) {
		super();
		this.filePath = filePath;
		this.fileOriginal = fileOriginal;
	}
	public String getFilePath() {
		return filePath;
	}
	public String getFileOriginal() {
		return fileOriginal;
	}
}
