package com.nhs.trust.teesesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author arif.mohammed
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class FileContents {
	
	@JsonProperty("name")
	private String fileName;
	@JsonProperty("data")
	private String fileData;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	
}
