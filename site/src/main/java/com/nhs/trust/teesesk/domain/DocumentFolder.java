package com.nhs.trust.teesesk.domain;

/**
 * @author arif.mohammed
 */
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentFolder {

	private List<DocumentFolder> subfolder;

	private int totalSize;
	private String folderName;
	private List<Document> entries;
	private String docId;
	private String template;
	private Long order;
	private String description;
	private String featuredImageId;
	private String path;
	private String headerTitle;
	
	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public List<Document> getEntries() {
		return entries;
	}

	public void setEntries(List<Document> entries) {
		this.entries = entries;
	}

	public List<DocumentFolder> getSubfolder() {
		return subfolder;
	}

	public void setSubfolder(List<DocumentFolder> subfolder) {
		this.subfolder = subfolder;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeaturedImageId() {
		return featuredImageId;
	}

	public void setFeaturedImageId(String featuredImageId) {
		this.featuredImageId = featuredImageId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHeaderTitle() {
		return headerTitle;
	}

	public void setHeaderTitle(String headerTitle) {
		this.headerTitle = headerTitle;
	}

	
}
