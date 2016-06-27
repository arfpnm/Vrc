package com.nhs.trust.teesesk.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TeesEskResourceProperties {

	private String uid;
	
	@JsonProperty("dc:description")
	private String description;
	
	@JsonProperty("tees:email")
	private String email;
	
	@JsonProperty("tees:featuredimage")
	private String featuredImage;
	
	@JsonProperty("tees:order")
	private String order;
	
	@JsonProperty("tees:logo")
	private String logoId;
	private String logoImageTitle;
	private String logoPathLink;
	
	@JsonProperty("tees:phone")
	private String phone;
	
	@JsonProperty("tees:contactdetailslabel")
	private String contactDetailsLabel;
	
	@JsonProperty("tees:hidefromnav")
	private String hideFromNav;
	
	@JsonProperty("tees:strapline")
	private String strapLine;
	
	@JsonProperty("tees:sitename")
	private String siteName;
	
	@JsonProperty("tees:htmltext")
	private String htmlText;
	
	@JsonProperty("tees:linkURL")
	private String linkUrl;
	
	@JsonProperty("tees:favicon")
	private String faviconId;
	private String faviconTitle;
	private String faviconPath;
	
	@JsonProperty("tees:socialmedia")
	private SocialMedia socialMedia;
	
	@JsonProperty("tees:footermenulabel1")
	private String footerMenuLabel1;
	@JsonProperty("tees:footermenulabel2")
	private String footerMenuLabel2;
	@JsonProperty("tees:footermenulabel3")
	private String footerMenuLabel3;
	@JsonProperty("tees:footermenulabel4")
	private String footerMenuLabel4;

	@JsonProperty("tees:footermenu1")
	private List<FooterMenu> footerMenu1List;
	@JsonProperty("tees:footermenu2")
	private List<FooterMenu> footerMenu2List;
	@JsonProperty("tees:footermenu3")
	private List<FooterMenu> footerMenu3List;
	@JsonProperty("tees:footermenu4")
	private List<FooterMenu> footerMenu4List;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFeaturedImage() {
		return featuredImage;
	}

	public void setFeaturedImage(String featuredImage) {
		this.featuredImage = featuredImage;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getLogoId() {
		return logoId;
	}

	public void setLogoId(String logoId) {
		this.logoId = logoId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactDetailsLabel() {
		return contactDetailsLabel;
	}

	public void setContactDetailsLabel(String contactDetailsLabel) {
		this.contactDetailsLabel = contactDetailsLabel;
	}

	public String getHideFromNav() {
		return hideFromNav;
	}

	public void setHideFromNav(String hideFromNav) {
		this.hideFromNav = hideFromNav;
	}

	public String getStrapLine() {
		return strapLine;
	}

	public void setStrapLine(String strapLine) {
		this.strapLine = strapLine;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getHtmlText() {
		return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getFaviconId() {
		return faviconId;
	}

	public void setFaviconId(String faviconId) {
		this.faviconId = faviconId;
	}

	public SocialMedia getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	public String getLogoImageTitle() {
		return logoImageTitle;
	}

	public void setLogoImageTitle(String logoImageTitle) {
		this.logoImageTitle = logoImageTitle;
	}

	public String getLogoPathLink() {
		return logoPathLink;
	}

	public void setLogoPathLink(String logoPathLink) {
		this.logoPathLink = logoPathLink;
	}

	public String getFaviconTitle() {
		return faviconTitle;
	}

	public void setFaviconTitle(String faviconTitle) {
		this.faviconTitle = faviconTitle;
	}

	public String getFaviconPath() {
		return faviconPath;
	}

	public void setFaviconPath(String faviconPath) {
		this.faviconPath = faviconPath;
	}

	public String getFooterMenuLabel1() {
		return footerMenuLabel1;
	}

	public void setFooterMenuLabel1(String footerMenuLabel1) {
		this.footerMenuLabel1 = footerMenuLabel1;
	}

	public String getFooterMenuLabel2() {
		return footerMenuLabel2;
	}

	public void setFooterMenuLabel2(String footerMenuLabel2) {
		this.footerMenuLabel2 = footerMenuLabel2;
	}

	public String getFooterMenuLabel3() {
		return footerMenuLabel3;
	}

	public void setFooterMenuLabel3(String footerMenuLabel3) {
		this.footerMenuLabel3 = footerMenuLabel3;
	}

	public String getFooterMenuLabel4() {
		return footerMenuLabel4;
	}

	public void setFooterMenuLabel4(String footerMenuLabel4) {
		this.footerMenuLabel4 = footerMenuLabel4;
	}

	public List<FooterMenu> getFooterMenu1List() {
		return footerMenu1List;
	}

	public void setFooterMenu1List(List<FooterMenu> footerMenu1List) {
		this.footerMenu1List = footerMenu1List;
	}

	public List<FooterMenu> getFooterMenu2List() {
		return footerMenu2List;
	}

	public void setFooterMenu2List(List<FooterMenu> footerMenu2List) {
		this.footerMenu2List = footerMenu2List;
	}

	public List<FooterMenu> getFooterMenu3List() {
		return footerMenu3List;
	}

	public void setFooterMenu3List(List<FooterMenu> footerMenu3List) {
		this.footerMenu3List = footerMenu3List;
	}

	public List<FooterMenu> getFooterMenu4List() {
		return footerMenu4List;
	}

	public void setFooterMenu4List(List<FooterMenu> footerMenu4List) {
		this.footerMenu4List = footerMenu4List;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}