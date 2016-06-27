package com.nhs.trust.teesesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SocialMedia {
	
	private String uid;
	
	@JsonProperty("twitter")
	private String twitter;
	
	@JsonProperty("facebook")
	private String facebook;
	
	@JsonProperty("linkedin")
	private String linkedIn;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFaceBook() {
		return facebook;
	}

	public void setFaceBook(String faceBook) {
		this.facebook = faceBook;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	

}
