package com.nhs.trust.teesesk.beans;

import java.util.List;

import org.hippoecm.hst.content.beans.Node;

@Node(jcrType = "nhstrustteesesk:TeesEskDocumentFolder")
public class TeesEskDocumentFolder extends BaseDocument {

	private List<TeesEskDocumentFolder> subfolder;

	private int totalSize;
	private String folderName;
	private List<TeesEskDocument> documents;
	private String docId;
	private String template;
	private Long order;
	private String description;
	private String featureImageId;
	private String relativePath;
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

	public List<TeesEskDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<TeesEskDocument> documents) {
		this.documents = documents;
	}

	public List<TeesEskDocumentFolder> getSubfolder() {
		return subfolder;
	}

	public void setSubfolder(List<TeesEskDocumentFolder> subfolder) {
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

	public String getFeatureImageId() {
		return featureImageId;
	}

	public void setFeatureImageId(String featureImageId) {
		this.featureImageId = featureImageId;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getHeaderTitle() {
		return headerTitle;
	}

	public void setHeaderTitle(String headerTitle) {
		this.headerTitle = headerTitle;
	}

}
