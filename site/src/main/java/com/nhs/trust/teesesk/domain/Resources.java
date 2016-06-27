package com.nhs.trust.teesesk.domain;

import java.util.List;

public class Resources {
	
	private String title;
	private String url;
	private String description;
	private List<String> subjectTags;
	private String type;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getSubjectTags() {
		return subjectTags;
	}
	public void setSubjectTags(List<String> subjectTags) {
		this.subjectTags = subjectTags;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
