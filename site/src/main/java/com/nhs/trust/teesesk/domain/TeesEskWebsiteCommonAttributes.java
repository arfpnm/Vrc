package com.nhs.trust.teesesk.domain;
/**
 * @author arif.mohammed
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TeesEskWebsiteCommonAttributes {

	private String uid;
	private String path;
	private String type;
	private String title;
	private String[] facets;

	@JsonProperty("properties")
	private TeesEskResourceProperties teesEskResourceProperties;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getFacets() {
		return facets;
	}

	public void setFacets(String[] facets) {
		this.facets = facets;
	}

	public TeesEskResourceProperties getTeesEskResourceProperties() {
		return teesEskResourceProperties;
	}

	public void setTeesEskResourceProperties(
			TeesEskResourceProperties teesEskResourceProperties) {
		this.teesEskResourceProperties = teesEskResourceProperties;
	}

}
