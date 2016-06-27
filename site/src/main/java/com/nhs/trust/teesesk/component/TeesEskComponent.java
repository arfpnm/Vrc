package com.nhs.trust.teesesk.component;

/**
 * @author arif.mohammed
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.CommonComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhs.trust.teesesk.beans.TeesEskDocument;
import com.nhs.trust.teesesk.beans.TeesEskDocumentFolder;
import com.nhs.trust.teesesk.domain.Breadcrumb;
import com.nhs.trust.teesesk.domain.Document;
import com.nhs.trust.teesesk.domain.DocumentFolder;
import com.nhs.trust.teesesk.domain.TeesEskWebsiteCommonAttributes;

public class TeesEskComponent extends CommonComponent {
	public static final Logger log = LoggerFactory.getLogger(TeesEskComponent.class);

	private final static String propName = "teesesk-integration.properties";
	protected DocumentFolder documentFolder;
	public static Properties properties = new Properties();

	@Override
	public void doBeforeRender(HstRequest request, HstResponse response) {
		super.doBeforeRender(request, response);
		// final HstRequestContext ctx = request.getRequestContext();
		populateProperties(propName);
		//properties = getPropFile();
	}

	protected TeesEskDocumentFolder getAllTeesEskDocumentNavFolders(String id) {

		if(id == null || id.isEmpty()) return null;
		DocumentFolder nuxeoDocFolder = getAllNavDocumentFolderFromNuxeo(id);
		TeesEskDocumentFolder teesEskDocumentFolder = new TeesEskDocumentFolder();
		List<Document> documentList = new ArrayList<Document>();
		documentList = nuxeoDocFolder != null ? nuxeoDocFolder.getEntries() : null;
		if(documentList == null || documentList.isEmpty()) return null;
		teesEskDocumentFolder.setDocuments(convertNuxeoDocToTeesEskDocumentNonHiddenList(documentList));
		teesEskDocumentFolder
		.setSubfolder(convertNuxeoDocumentFoldersToTeesEskDocumentFolders(nuxeoDocFolder.getSubfolder()));
		teesEskDocumentFolder.setFolderName(nuxeoDocFolder.getFolderName());
		return teesEskDocumentFolder;
	}


	protected TeesEskDocumentFolder getAllTeesEskDocumentNavFoldersForPath(String path) {

		if(path == null || path.isEmpty()) return null;
		DocumentFolder nuxeoDocFolder = getAllNavDocumentFolderForPath(path);
		TeesEskDocumentFolder teesEskDocumentFolder = new TeesEskDocumentFolder();
		List<Document> documentList = new ArrayList<Document>();
		documentList = nuxeoDocFolder != null ? nuxeoDocFolder.getEntries() : null;
		if(documentList == null || documentList.isEmpty()) return null;
		teesEskDocumentFolder.setDocuments(convertNuxeoDocToTeesEskDocumentNonHiddenList(documentList));
		teesEskDocumentFolder
		.setSubfolder(convertNuxeoDocumentFoldersToTeesEskDocumentFolders(nuxeoDocFolder.getSubfolder()));
		teesEskDocumentFolder.setFolderName(nuxeoDocFolder.getFolderName());
		return teesEskDocumentFolder;
	}

	protected TeesEskDocument getTeesEskDocumentByPath(String path) {
		if(path == null || path.isEmpty()) return null;
		Document nuxeoDoc = getDocumentByPath(path);
		return convertDocumentToTeesEskDocument(nuxeoDoc);
	}


	protected TeesEskDocumentFolder getTopLevelFolders(String folderName) {

		DocumentFolder nuxeoDocFolder = getTopLevelFoldersFromNuxeo(folderName);
		TeesEskDocumentFolder TeesEskDocumentFolder = new TeesEskDocumentFolder();
		List<Document> documentList = new ArrayList<Document>();
		documentList = nuxeoDocFolder != null ? nuxeoDocFolder.getEntries() : null;
		TeesEskDocumentFolder.setDocuments(convertNuxeoDocToTeesEskDocumentNonHiddenList(documentList));
		return TeesEskDocumentFolder;
	}

	/**
	 * @return TeesEskWebsiteCommonAttributes Gets the common attributes for
	 *         TeesEsk
	 */
	protected TeesEskWebsiteCommonAttributes getWebsiteCommonAttributesDocument() {
		try {
			return getTeesEskWebCommonAttributesDocById(properties.getProperty("teesesk.nuxeo.commonattribute.id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private TeesEskWebsiteCommonAttributes getTeesEskWebCommonAttributesDocById(String id) throws Exception {
		String jsonResponse = getTeesEskWebCommomnAttributesDocByIdFromNuxeo(id);
		TeesEskWebsiteCommonAttributes teesEskWebsiteCommonAttributes = null;
		try {
			teesEskWebsiteCommonAttributes = new ObjectMapper().readValue(jsonResponse,
					TeesEskWebsiteCommonAttributes.class);
			if (teesEskWebsiteCommonAttributes != null
					&& teesEskWebsiteCommonAttributes.getTeesEskResourceProperties() != null) {

				String logoImageId = teesEskWebsiteCommonAttributes.getTeesEskResourceProperties().getLogoId();
				if (logoImageId != null) {
					Document document = getTeesEskDocumentById(logoImageId, true);
					if (document != null && document.getProperties() != null
							&& document.getProperties().getFileContents() != null) {
						teesEskWebsiteCommonAttributes.getTeesEskResourceProperties()
						.setLogoPathLink(document.getProperties().getFileContents().getFileData());
						teesEskWebsiteCommonAttributes.getTeesEskResourceProperties()
						.setLogoImageTitle(document.getTitle());
					}
				}
			}
			if (teesEskWebsiteCommonAttributes != null
					&& teesEskWebsiteCommonAttributes.getTeesEskResourceProperties() != null) {

				String feviconImageId = teesEskWebsiteCommonAttributes.getTeesEskResourceProperties().getFaviconId();
				if (feviconImageId != null) {
					Document document = getTeesEskDocumentById(feviconImageId, true);
					if (document != null && document.getProperties() != null
							&& document.getProperties().getFileContents() != null) {
						teesEskWebsiteCommonAttributes.getTeesEskResourceProperties()
						.setFaviconPath(document.getProperties().getFileContents().getFileData());
						teesEskWebsiteCommonAttributes.getTeesEskResourceProperties()
						.setFaviconTitle(document.getTitle());
					}
				}
			}
		} catch (JsonParseException e) {
			throw new Exception(e);
		} catch (JsonMappingException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}
		return teesEskWebsiteCommonAttributes;
	}

	private String getTeesEskWebCommomnAttributesDocByIdFromNuxeo(String id) {

		StringBuilder urlBuilder = new StringBuilder(properties.getProperty("teesesk.nuxeo.connection"));
		urlBuilder.append(properties.getProperty("teesesk.nuxeo.folder.name"));
		urlBuilder.append(properties.getProperty("teesesk.nuxeo.webattribute.folder"));
		urlBuilder.append(id);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseMessage = restTemplate.getForEntity(urlBuilder.toString(), String.class);
		return responseMessage.getBody();
	}

	private List<TeesEskDocument> convertNuxeoDocToTeesEskDocumentNonHiddenList(List<Document> documentList) {
		TeesEskDocument teesEskDocument = null;
		List<TeesEskDocument> teesEskDocuments = new ArrayList<TeesEskDocument>();
		if(documentList == null) return null;
		for (Document document : documentList) {
			// if (!document.isFolder()) {
			teesEskDocument = new TeesEskDocument();
			teesEskDocument.setDocId(document.getUid());
			teesEskDocument.setTitle(document.getTitle());
			teesEskDocument.setPath(document.getPath());
			teesEskDocument.setRelativePath(createPathList(document.getPath()));
			teesEskDocument.setType(document.getType());
			teesEskDocument.setDocId(document.getUid());
			teesEskDocument.setRepository(document.getRepository());
			teesEskDocument.setLastModified(document.getLastModified());
			String[] facets = document.getFacets();
			if (facets != null && facets.length != 0) {
				for (String facetsStr : facets) {
					if (facetsStr.equalsIgnoreCase("Folderish")) {
						teesEskDocument.setFolder(true);
					}
				}
			}
			com.nhs.trust.teesesk.domain.Properties metadataProperties = document.getProperties();
			teesEskDocument.setNuxeoUid(document.getUid());
			if (metadataProperties != null) {
				teesEskDocument.setOriginalName(metadataProperties.getFileContents() != null
						? metadataProperties.getFileContents().getFileName() : null);
				teesEskDocument.setFileData(metadataProperties.getFileContents() != null
						? metadataProperties.getFileContents().getFileData() : null);
				teesEskDocument.setDescription(metadataProperties.getDescription());
				teesEskDocument.setNote(metadataProperties.getNote());
				teesEskDocument.setHeaderimage(metadataProperties.getHeaderimage());
				teesEskDocument.setHospitals(metadataProperties.getHospitals());
				teesEskDocument.setLazyloading(metadataProperties.getLazyloading());
				teesEskDocument.setMoreInformationList(metadataProperties.getMoreInformationList());
				teesEskDocument.setRelateddocuments(metadataProperties.getRelateddocuments());
				teesEskDocument.setServices(metadataProperties.getServices());
				teesEskDocument.setTrustInfos(
						formatMixedStringLinksWithMultipleValuesToMap(metadataProperties.getTrustinfo()));
				teesEskDocument.setTrustInfo(formatMixedStringLinkToMap(metadataProperties.getTrustinfo()));
				teesEskDocument
				.setReferrer(formatMixedStringLinksWithMultipleValuesToMap(metadataProperties.getReferrer()));
				teesEskDocument.setRecruitment(
						formatMixedStringLinksWithMultipleValuesToMap(metadataProperties.getRecruitment()));
				teesEskDocument.setGetInvolved(
						formatMixedStringLinksWithMultipleValuesToMap(metadataProperties.getGetInvolved()));
				teesEskDocument.setAddress(metadataProperties.getAddress());
				teesEskDocument
				.setLocationAndhospitalMap(formatMixedStringLinkToMap(metadataProperties.getHospitals()));
				teesEskDocument.setMoreInformationMap(
						formatMixedStringLinksWithMultipleValuesToMap(metadataProperties.getMoreInformationList()));
				teesEskDocument.setHippoTemplateName(metadataProperties.getHstSiteMap() != null
						? metadataProperties.getHstSiteMap().getHippoTemplateName() : null);
				teesEskDocument.setIsSyncRequired(metadataProperties.getHstSiteMap() != null
						? metadataProperties.getHstSiteMap().getIsSyncRequired() : null);
				teesEskDocument.setListingImageId(metadataProperties.getListingImageId());
				teesEskDocument.setPhone(metadataProperties.getPhone());
				teesEskDocument.setCqc(metadataProperties.getCqc());
				teesEskDocument.setOrder(metadataProperties.getOrder());
				teesEskDocument.setSectionType(metadataProperties.getSectionType());
				teesEskDocument.setLocationSelector(metadataProperties.getLocationSelector());
				teesEskDocument.setHeaderTitle(metadataProperties.getHeaderTitle());
			}
			if (!metadataProperties.isTeesFolderHiddenForNavigation()) {
				teesEskDocuments.add(teesEskDocument);
			}
			// }
		}
		return sortDocuments(teesEskDocuments);
		// return teesEskDocuments;
	}

	private List<TeesEskDocumentFolder> convertNuxeoDocumentFoldersToTeesEskDocumentFolders(
			List<DocumentFolder> nuxeoDocumentFolders) {
		if (nuxeoDocumentFolders == null) {
			return null;
		}
		TeesEskDocumentFolder teesEskDocumentSubFolder = null;
		List<TeesEskDocumentFolder> teesEskDocumentFolders = new ArrayList<TeesEskDocumentFolder>();
		for (DocumentFolder documentFolder : nuxeoDocumentFolders) {
			teesEskDocumentSubFolder = new TeesEskDocumentFolder();
			teesEskDocumentSubFolder.setFolderName(documentFolder.getFolderName());
			teesEskDocumentSubFolder.setHeaderTitle(documentFolder.getHeaderTitle());
			teesEskDocumentSubFolder.setDocId(documentFolder.getDocId());
			teesEskDocumentSubFolder.setOrder(documentFolder.getOrder());
			teesEskDocumentSubFolder.setDocId(documentFolder.getDocId());
			teesEskDocumentSubFolder.setDescription(documentFolder.getDescription());
			teesEskDocumentSubFolder.setFeatureImageId(documentFolder.getFeaturedImageId());
			teesEskDocumentSubFolder
			.setDocuments(convertNuxeoDocToTeesEskDocumentNonHiddenList(documentFolder.getEntries()));
			teesEskDocumentSubFolder.setTotalSize(documentFolder.getTotalSize());
			teesEskDocumentSubFolder.setRelativePath(createPathList(documentFolder.getPath()));
			teesEskDocumentFolders.add(teesEskDocumentSubFolder);
			List<DocumentFolder> subFolders = documentFolder.getSubfolder();
			if (subFolders != null && !subFolders.isEmpty()) {
				teesEskDocumentSubFolder.setSubfolder(convertNuxeoDocumentFoldersToTeesEskDocumentFolders(subFolders));
			}
		}
		return sortDocumentFolders(teesEskDocumentFolders);
		//return teesEskDocumentFolders;
	}

	/**
	 * @param folderName
	 * @return List<TeesEskDocument> This method will retrieve only the
	 *         documents under the folder (folderName)
	 */
	protected List<TeesEskDocument> getTeesEskFolderDocuments(String folderName) {

		List<TeesEskDocument> teesEskDocumentSet = null;
		try {
			teesEskDocumentSet = new ArrayList<TeesEskDocument>();
			String jsonString = getDocumentFolderFromNuxeo(folderName);
			DocumentFolder nuxeoDocFolder = new ObjectMapper().readValue(jsonString, DocumentFolder.class);
			List<Document> documentList = new ArrayList<Document>();

			this.documentFolder = nuxeoDocFolder;
			if (documentFolder != null) {
				documentList = nuxeoDocFolder != null ? nuxeoDocFolder.getEntries() : null;

				if (documentList != null && !documentList.isEmpty()) {
					for (Document document : documentList) {
						String[] facets = document.getFacets();
						if (facets != null && facets.length != 0) {
							for (String facetsStr : facets) {
								if (!facetsStr.equalsIgnoreCase("Folderish")) {
									teesEskDocumentSet.add(convertDocumentToTeesEskDocument(document));
									break;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sortDocuments(teesEskDocumentSet);
	}

	protected List<TeesEskDocument> getTeesEskSubfoldersOnly(String folderName) {

		List<TeesEskDocument> teesEskDocumentSet = null;
		try {
			teesEskDocumentSet = new ArrayList<TeesEskDocument>();
			String jsonString = getDocumentFolderFromNuxeo(folderName);
			DocumentFolder nuxeoDocFolder = new ObjectMapper().readValue(jsonString, DocumentFolder.class);
			List<Document> documentList = new ArrayList<Document>();
			if (nuxeoDocFolder != null) {
				documentList = nuxeoDocFolder != null ? nuxeoDocFolder.getEntries() : null;
				if (documentList != null && !documentList.isEmpty()) {
					for (Document document : documentList) {
						String[] facets = document.getFacets();
						List<String> folderType = facets != null && facets.length != 0 ? Arrays.asList(facets) : null;
						if (folderType != null && folderType.stream().anyMatch(t -> t.equalsIgnoreCase("Folderish"))) {
							teesEskDocumentSet.add(convertDocumentToTeesEskDocument(document));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sortDocuments(teesEskDocumentSet);
	}

	protected List<TeesEskDocument> getTeesEskDocumentForSearchText(String searchText) {

		List<TeesEskDocument> teesEskDocumentSet = new ArrayList<TeesEskDocument>();
		DocumentFolder nuxeoDocFolder = getDocumentFromNuxeoForSearchText(searchText);
		List<Document> documentList = new ArrayList<Document>();
		documentList = nuxeoDocFolder != null ? nuxeoDocFolder.getEntries() : null;
		this.documentFolder = nuxeoDocFolder;
		if (documentList != null && !documentList.isEmpty()) {
			for (Document document : documentList) {
				teesEskDocumentSet.add(convertDocumentToTeesEskDocument(document));
			}
		}
		return sortDocuments(teesEskDocumentSet);
	}

	protected TeesEskDocument getDocumentById(String id) {
		if (id == null || id.isEmpty())
			return null;
		return convertDocumentToTeesEskDocument(getTeesEskDocumentById(id, false));
	}

	protected TeesEskDocument getDocumentWithResourcesById(String id, boolean isResourceRequired) {
		if (id == null || id.isEmpty())
			return null;
		return convertDocumentToTeesEskDocument(getTeesEskDocumentById(id, isResourceRequired));
	}

	private TeesEskDocument convertDocumentToTeesEskDocument(Document document) {
		if (document == null)
			return null;
		TeesEskDocument hippoDocument = new TeesEskDocument();
		hippoDocument.setDocId(document.getUid());
		hippoDocument.setTitle(document.getTitle());
		hippoDocument.setPath(document.getPath());
		hippoDocument.setRelativePath(createPathList(document.getPath()));
		hippoDocument.setType(document.getType());
		String[] facets = document.getFacets();
		if (facets != null && facets.length != 0) {
			for (String facetsStr : facets) {
				if (facetsStr.equalsIgnoreCase("Folderish")) {
					hippoDocument.setFolder(true);
				}
			}
		}
		com.nhs.trust.teesesk.domain.Properties metadataProperties = document.getProperties();
		if (metadataProperties != null) {
			hippoDocument.setDescription(metadataProperties.getDescription());
			hippoDocument.setNote(metadataProperties.getNote());
			hippoDocument.setOrder(metadataProperties.getOrder());
			hippoDocument.setHiddenForNavigation(metadataProperties.isTeesFolderHiddenForNavigation());
			hippoDocument.setTeesLinkUrl(metadataProperties.getTeesLinkUrl());
			hippoDocument.setTeesHtmlText(metadataProperties.getTeesHtmltext());
			hippoDocument.setSectionType(metadataProperties.getSectionType());
			String pageDocumentImageId = metadataProperties.getTeesFeatureImageId();
			hippoDocument.setLocationSelector(metadataProperties.getLocationSelector());
			hippoDocument.setHeaderTitle(metadataProperties.getHeaderTitle());
			if (pageDocumentImageId != null) {
				Document imageDocument = null;
				try {
					imageDocument = getTeesEskDocumentById(pageDocumentImageId, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (imageDocument != null && imageDocument.getProperties() != null
						&& imageDocument.getProperties().getFileContents() != null) {
					hippoDocument.setFeaturedImageLink(imageDocument.getProperties().getFileContents().getFileData());
					hippoDocument.setFeaturedImageTitle(imageDocument.getTitle());
				}
			}
		}

		if (document.getContextParameters() != null) {
			String domain = properties.getProperty("teesesk.nuxeo.domain.folder");
			String section = properties.getProperty("teesesk.nuxeo.section.folder");
			String root = properties.getProperty("teesesk.nuxeo.root.folder");

			Breadcrumb breadCrumb = document.getContextParameters().getBreadCrumb();
			TeesEskDocument breadcrumb = null;
			List<TeesEskDocument> breadcrumbs = new ArrayList<TeesEskDocument>();
			for (Document breadcrumbDoc : breadCrumb.getEntries()) {
				breadcrumb = new TeesEskDocument();
				breadcrumb.setTitle(breadcrumbDoc.getTitle());
				breadcrumb.setPath(breadcrumbDoc.getPath());
				breadcrumb.setDocId(breadcrumbDoc.getUid());

				if (breadcrumbDoc.getTitle().equalsIgnoreCase(domain)
						|| breadcrumbDoc.getTitle().equalsIgnoreCase(section)
						|| breadcrumbDoc.getTitle().equalsIgnoreCase(root)) {
					continue;
				}
				breadcrumbs.add(breadcrumb);
			}
			hippoDocument.setBreadcrumbs(breadcrumbs);
		}
		return hippoDocument;
	}

	private List<TeesEskDocument> sortDocuments(List<TeesEskDocument> documentList){
		documentList.sort(Comparator.comparing(e -> (e != null && e.getOrder() != null ) ? e.getOrder() : 0));
		return documentList;
	}

	private List<TeesEskDocumentFolder> sortDocumentFolders(List<TeesEskDocumentFolder> documentFolderList){
		documentFolderList.sort(Comparator.comparing(e -> (e != null && e.getOrder() != null ) ? e.getOrder() : 0));
		return documentFolderList;
	}


	/**
	 * @param folderName
	 * @return DocumnetFolder api: /document-request/tees/TeesEsk/{folder}
	 */
	private DocumentFolder getTopLevelFoldersFromNuxeo(String folderName) {
		StringBuilder urlBuilder = new StringBuilder(properties.getProperty("teesesk.nuxeo.connection"));
		urlBuilder.append(folderName + "/");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<DocumentFolder> responseMessage = restTemplate.getForEntity(urlBuilder.toString(),
				DocumentFolder.class);
		return responseMessage != null ? responseMessage.getBody() : null;
	}

	// Eg: Carousel Items
	private String getDocumentFolderFromNuxeo(String folderName) {
		ResponseEntity<String> responseMessage = null;
		try {
			StringBuilder urlBuilder = new StringBuilder(properties.getProperty("teesesk.nuxeo.connection"));
			urlBuilder.append(properties.getProperty("teesesk.nuxeo.folder.name"));
			urlBuilder.append(folderName + "/");
			RestTemplate restTemplate = new RestTemplate();
			responseMessage = restTemplate.getForEntity(urlBuilder.toString(), String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return responseMessage != null ? responseMessage.getBody() : null;
	}

	private DocumentFolder getAllNavDocumentFolderFromNuxeo(String id) {
		if(id != null && id.contains("/"))
			id = id.replace("/", "|");
		ResponseEntity<DocumentFolder> responseMessage = null;
		try {
			StringBuilder urlBuilder = new StringBuilder(properties.getProperty("teesesk.nuxeo.connection"));
			urlBuilder.append("all-documents-for-id/");
			urlBuilder.append(id);
			RestTemplate restTemplate = new RestTemplate();
			responseMessage = restTemplate.getForEntity(urlBuilder.toString(), DocumentFolder.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return responseMessage != null ? responseMessage.getBody() : null;
	}

	private DocumentFolder getAllNavDocumentFolderForPath(String path) {
		if(path != null && path.contains("/"))
			path = path.replace("/", "|");
		ResponseEntity<DocumentFolder> responseMessage = null;
		try {
			StringBuilder urlBuilder = new StringBuilder(properties.getProperty("teesesk.nuxeo.connection"));
			urlBuilder.append("all-documents-for-path/");
			urlBuilder.append(path);
			RestTemplate restTemplate = new RestTemplate();
			responseMessage = restTemplate.getForEntity(urlBuilder.toString(), DocumentFolder.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return responseMessage != null ? responseMessage.getBody() : null;
	}

	private Document getDocumentByPath(String path) {
		if(path != null && path.contains("/"))
			path = path.replace("/", "|");
		ResponseEntity<Document> responseMessage = null;
		try {
			StringBuilder urlBuilder = new StringBuilder(properties.getProperty("teesesk.nuxeo.connection"));
			urlBuilder.append("document-for-path/");
			urlBuilder.append(path);
			RestTemplate restTemplate = new RestTemplate();
			responseMessage = restTemplate.getForEntity(urlBuilder.toString(), Document.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return responseMessage != null ? responseMessage.getBody() : null;
	}



	public Document getTeesEskDocumentById(String id, Boolean isForResource) {
		try {
			StringBuilder urlBuilder = new StringBuilder(properties.getProperty("teesesk.nuxeo.connection"));
			urlBuilder.append(properties.getProperty("teesesk.nuxeo.folder.name"));
			urlBuilder.append(id);
			urlBuilder.append("/");
			urlBuilder.append(isForResource);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Document> responseMessage = restTemplate.getForEntity(urlBuilder.toString(), Document.class);
			return responseMessage.getBody();
		} catch (HttpServerErrorException e) {
			if (e.getStatusCode().value() == 500) {
				log.error("Id " + id + " Not Found in Nuxeo Content ");
			}
			return null;
		}
	}

	private DocumentFolder getDocumentFromNuxeoForSearchText(String text) {
		// populateProperties(propName);
		StringBuilder urlBuilder = new StringBuilder(properties.getProperty("teesesk.nuxeo.connection"));
		urlBuilder.append(properties.getProperty("teesesk.nuxeo.search"));
		urlBuilder.append(text);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<DocumentFolder> responseMessage = restTemplate.getForEntity(urlBuilder.toString(),
				DocumentFolder.class);
		return responseMessage != null ? responseMessage.getBody() : null;
	}

	private static void populateProperties(String propertyName) {
		try {
			//properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyName));
			properties.load(new FileInputStream("/usr/local/etc/teesesk-integration.properties"));

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public String getDocumentPath(String path) {
		if (path == null)
			return null;
		String rootName = properties.getProperty("teesesk.nuxeo.folder.name");
		StringBuilder str2 = new StringBuilder(path);
		return str2.substring(str2.indexOf(rootName) + rootName.length(), path.length()).replaceAll("/", "|");
	}

	private String createPathList(String path) {
		if (path == null || path.isEmpty())
			return null;
		String rootName = properties.getProperty("teesesk.nuxeo.folder.name");
		StringBuilder str2 = new StringBuilder(path);
		return str2.substring(str2.indexOf(rootName) + rootName.length(), path.length());
	}

	private Map<String, Set<String>> formatMixedStringLinksWithMultipleValuesToMap(List<String> generalLinksString) {
		if (generalLinksString == null || generalLinksString.isEmpty()) {
			return null;
		}
		Map<String, Set<String>> linkGenerationMap = new HashMap<String, Set<String>>();
		Set<String> subCategoryList = new HashSet<String>();
		String mainHeader = null;
		String subHeader = null;
		for (String mixedLinkString : generalLinksString) {
			String[] mixedLinkStringArray = mixedLinkString.split("/");
			if (mixedLinkStringArray != null && mixedLinkStringArray.length > 0) {
				if (mixedLinkStringArray.length > 1) {
					mainHeader = mixedLinkStringArray[0];
					subHeader = mixedLinkStringArray[1];
					if (linkGenerationMap.containsKey(mainHeader)) {
						subCategoryList = linkGenerationMap.get(mainHeader);
						subCategoryList.add(subHeader);
						linkGenerationMap.put(mainHeader, subCategoryList);
					} else {
						subCategoryList = new HashSet<String>();
						subCategoryList.add(subHeader);
						linkGenerationMap.put(mainHeader, subCategoryList);
					}
				} else {
					mainHeader = mixedLinkStringArray[0];
					linkGenerationMap.put(mainHeader, new HashSet<String>());
				}
			}
		}
		return linkGenerationMap;
	}

	private Map<String, String> formatMixedStringLinkToMap(List<String> generalLinksString) {
		if (generalLinksString == null || generalLinksString.isEmpty()) {
			return null;
		}
		Map<String, String> linkGenerationMap = new HashMap<String, String>();
		String mainHeader = null;
		String subHeader = null;
		for (String mixedLinkString : generalLinksString) {
			String[] mixedLinkStringArray = mixedLinkString.split("/");
			if (mixedLinkStringArray != null && mixedLinkStringArray.length > 0) {
				if (mixedLinkStringArray.length > 1) {
					mainHeader = mixedLinkStringArray[0];
					subHeader = mixedLinkStringArray[1];
					linkGenerationMap.put(mainHeader, subHeader);
				}
			}
		}
		return linkGenerationMap;
	}

	public String getUrlParameters(String path){
		if(path != null && path.contains("/")){
			Pattern otherPattern = Pattern.compile("./(.+)");
			String parentFolder = "";
			Matcher matcher = otherPattern.matcher(path);
			if (matcher.find()) {
				parentFolder = matcher.group(1);
			}
			return parentFolder;
		}
		return path;
	}

	public String getParentPath(String path){
		if(path != null && path.contains("/")){
			path = path.substring(1,path.length());
			Pattern otherPattern = Pattern.compile("/([^/]*)");
			String parentFolder = "";
			Matcher matcher = otherPattern.matcher(path);
			if (matcher.find()) {
				parentFolder = matcher.group(1);
			}
			return parentFolder;
		}
		return path;
	}

}