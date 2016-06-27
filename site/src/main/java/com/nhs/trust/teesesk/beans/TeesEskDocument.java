package com.nhs.trust.teesesk.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import com.nhs.trust.teesesk.domain.AboutInfo;
import com.nhs.trust.teesesk.domain.Address;
import com.nhs.trust.teesesk.domain.FileContents;
import com.nhs.trust.teesesk.domain.RelatedDocument;

@Node(jcrType = "nhstrustteesesk:TeesEskDocument")
public class TeesEskDocument extends BaseDocument {

	private String docId;
	private String title;
	private HippoHtml content;
	private String docDetails;
	private String originalName;
	private String fileData;
	private String path;
	private String repository;
	private String type;
	private String description;
	private String note;
	private String phone;
	private String dataLat;
	private String dataLng;
	private String teesLinkUrl;
	private String teesHtmlText;
	private Set<String> newsLocation;
	private String headerTitle;
	private String headerimage;
	private Map<String, Set<String>> trustInfos;
	private Map<String, String> trustInfo;
	private String featured;
	private String listingImageId;
	private Map<String, Set<String>> locationAndhospitalsMap;
	private Map<String, String> locationAndhospitalMap;
	private List<String> relateddocuments;
	private String lazyloading;
	private String documentType;
	private Address address;
	private List<String> moreInformationList;
	private Set<String> services;
	private List<String> hospitals;
	private Map<String, Set<String>> moreInformationMap;
	private List<FileContents> headerImages;
	private FileContents listingImage;
	private String hippoTemplateName;
	private Boolean isSyncRequired;
	private String cqc;
	private String menuTitle;
	private String directions;
	private String link;
	private String nuxeoUid;
	private Map<String, Set<String>> recruitment;
	private Map<String, Set<String>> getInvolved;
	private Map<String, Set<String>> referrer;
	private Map<String, AboutInfo> aboutInfo;
	private List<RelatedDocument> relatedDocumentList;
	private boolean isFolder;
	private String folderName;
	private boolean isHiddenForNavigation;
	private List<TeesEskDocument> breadcrumbs;
	private String featuredImageLink;
	private String featuredImageTitle;
	private Long order;
	private String sectionType;
	private String[] locationSelector;
	private String locationImageTitle;
	private String locationImageLink;
	private String relativePath;

	private Date lastModified;

	public String getTitle() {
		return title == null ? (String) getProperty("nhstrustteesesk:title") : title;
	}

	public HippoHtml getContent() {
		return content == null ? (HippoHtml) getBean("nhstrustteesesk:content") : content;
	}

	// public String getType() {
	// return type == null ? (String) getBean("nhstrust:type") : type;
	// }

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public void setContent(HippoHtml content) {
		this.content = content;
	}

	public String getDocDetails() {
		return docDetails;
	}

	public void setDocDetails(String docDetails) {
		this.docDetails = docDetails;
	}

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public Set<String> getNewsLocation() {
		return newsLocation;
	}

	public void setNewsLocation(Set<String> newsLocation) {
		this.newsLocation = newsLocation;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDataLat() {
		return dataLat;
	}

	public void setDataLat(String dataLat) {
		this.dataLat = dataLat;
	}

	public String getDataLng() {
		return dataLng;
	}

	public void setDataLng(String dataLng) {
		this.dataLng = dataLng;
	}

	public String getHeaderimage() {
		return headerimage;
	}

	public void setHeaderimage(String headerimage) {
		this.headerimage = headerimage;
	}

	public Map<String, Set<String>> getTrustInfos() {
		return trustInfos;
	}

	public void setTrustInfos(Map<String, Set<String>> trustinfos) {
		this.trustInfos = trustinfos;
	}

	public Map<String, String> getTrustInfo() {
		return trustInfo;
	}

	public void setTrustInfo(Map<String, String> trustInfo) {
		this.trustInfo = trustInfo;
	}

	public String getFeatured() {
		return featured;
	}

	public void setFeatured(String featured) {
		this.featured = featured;
	}

	public List<String> getHospitals() {
		return hospitals;
	}

	public void setHospitals(List<String> hospitals) {
		this.hospitals = hospitals;
	}

	public List<String> getRelateddocuments() {
		return relateddocuments;
	}

	public void setRelateddocuments(List<String> relateddocuments) {
		this.relateddocuments = relateddocuments;
	}

	public String getLazyloading() {
		return lazyloading;
	}

	public void setLazyloading(String lazyloading) {
		this.lazyloading = lazyloading;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getMoreInformationList() {
		return moreInformationList;
	}

	public void setMoreInformationList(List<String> moreInformationList) {
		this.moreInformationList = moreInformationList;
	}

	public Set<String> getServices() {
		return services;
	}

	public void setServices(Set<String> services) {
		this.services = services;
	}

	public Map<String, Set<String>> getLocationAndhospitalsMap() {
		return locationAndhospitalsMap;
	}

	public void setLocationAndhospitalsMap(Map<String, Set<String>> locationAndhospitalsMap) {
		this.locationAndhospitalsMap = locationAndhospitalsMap;
	}

	public Map<String, String> getLocationAndhospitalMap() {
		return locationAndhospitalMap;
	}

	public void setLocationAndhospitalMap(Map<String, String> locationAndhospitalMap) {
		this.locationAndhospitalMap = locationAndhospitalMap;
	}

	public Map<String, Set<String>> getMoreInformationMap() {
		return moreInformationMap;
	}

	public void setMoreInformationMap(Map<String, Set<String>> moreInformationMap) {
		this.moreInformationMap = moreInformationMap;
	}

	public List<FileContents> getHeaderImages() {
		return headerImages;
	}

	public void setHeaderImages(List<FileContents> headerImages) {
		this.headerImages = headerImages;
	}

	public FileContents getListingImage() {
		return listingImage;
	}

	public void setListingImage(FileContents listingImage) {
		this.listingImage = listingImage;
	}

	public String getHippoTemplateName() {
		return hippoTemplateName;
	}

	public void setHippoTemplateName(String hippoTemplateName) {
		this.hippoTemplateName = hippoTemplateName;
	}

	public Boolean getIsSyncRequired() {
		return isSyncRequired;
	}

	public void setIsSyncRequired(Boolean isSyncRequired) {
		this.isSyncRequired = isSyncRequired;
	}

	public String getCqc() {
		return cqc;
	}

	public void setCqc(String cqc) {
		this.cqc = cqc;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNuxeoUid() {
		return nuxeoUid;
	}

	public void setNuxeoUid(String nuxeoUid) {
		this.nuxeoUid = nuxeoUid;
	}

	public Map<String, Set<String>> getRecruitment() {
		return recruitment;
	}

	public void setRecruitment(Map<String, Set<String>> recruitment) {
		this.recruitment = recruitment;
	}

	public Map<String, Set<String>> getGetInvolved() {
		return getInvolved;
	}

	public void setGetInvolved(Map<String, Set<String>> getinvolved) {
		this.getInvolved = getinvolved;
	}

	public Map<String, Set<String>> getReferrer() {
		return referrer;
	}

	public void setReferrer(Map<String, Set<String>> referrer) {
		this.referrer = referrer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nuxeoUid == null) ? 0 : nuxeoUid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeesEskDocument other = (TeesEskDocument) obj;
		if (nuxeoUid == null) {
			if (other.nuxeoUid != null)
				return false;
		} else if (!nuxeoUid.equals(other.nuxeoUid))
			return false;
		return true;
	}

	public String getListingImageId() {
		return listingImageId;
	}

	public void setListingImageId(String listingImageId) {
		this.listingImageId = listingImageId;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Map<String, AboutInfo> getAboutInfo() {
		return aboutInfo;
	}

	public void setAboutInfo(Map<String, AboutInfo> aboutInfo) {
		this.aboutInfo = aboutInfo;
	}

	public List<RelatedDocument> getRelatedDocumentList() {
		return relatedDocumentList;
	}

	public void setRelatedDocumentList(List<RelatedDocument> relatedDocumentList) {
		this.relatedDocumentList = relatedDocumentList;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public boolean isHiddenForNavigation() {
		return isHiddenForNavigation;
	}

	public void setHiddenForNavigation(boolean isHiddenForNavigation) {
		this.isHiddenForNavigation = isHiddenForNavigation;
	}

	public List<TeesEskDocument> getBreadcrumbs() {
		return breadcrumbs;
	}

	public void setBreadcrumbs(List<TeesEskDocument> breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}

	public String getFeaturedImageLink() {
		return featuredImageLink;
	}

	public void setFeaturedImageLink(String featuredImageLink) {
		this.featuredImageLink = featuredImageLink;
	}

	public String getFeaturedImageTitle() {
		return featuredImageTitle;
	}

	public void setFeaturedImageTitle(String featuredImageTitle) {
		this.featuredImageTitle = featuredImageTitle;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getTeesLinkUrl() {
		return teesLinkUrl;
	}

	public void setTeesLinkUrl(String teesLinkUrl) {
		this.teesLinkUrl = teesLinkUrl;
	}

	public String getTeesHtmlText() {
		return teesHtmlText;
	}

	public void setTeesHtmlText(String teesHtmlText) {
		this.teesHtmlText = teesHtmlText;
	}

	public String getSectionType() {
		return sectionType;
	}

	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}

	public String[] getLocationSelector() {
		return locationSelector;
	}

	public void setLocationSelector(String[] locationSelector) {
		this.locationSelector = locationSelector;
	}

	public String getLocationImageTitle() {
		return locationImageTitle;
	}

	public void setLocationImageTitle(String locationImageTitle) {
		this.locationImageTitle = locationImageTitle;
	}

	public String getLocationImageLink() {
		return locationImageLink;
	}

	public void setLocationImageLink(String locationImageLink) {
		this.locationImageLink = locationImageLink;
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