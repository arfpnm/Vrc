package com.nhs.trust.teesesk.component;

import java.util.Arrays;
import java.util.List;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.trust.teesesk.beans.TeesEskDocument;
import com.nhs.trust.teesesk.beans.TeesEskDocumentFolder;

public class TeesEskLocationListingComponent extends TeesEskComponent {

	public static final Logger log = LoggerFactory.getLogger(TeesEskLocationListingComponent.class);

	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) {
		super.doBeforeRender(request, response);
		String parameter=request.getPathInfo();
		//parameter = parameter != null ? parameter.substring(parameter.lastIndexOf("/")+1,parameter.length()) : null;
		parameter = getUrlParameters(parameter);
		TeesEskDocumentFolder locationAndContact = getAllTeesEskDocumentNavFoldersForPath(parameter);
		List<TeesEskDocument> documents = locationAndContact != null ? locationAndContact.getDocuments() : null;
		for(TeesEskDocument nuxeoDocument : documents){
			if(nuxeoDocument.getListingImageId() != null && !nuxeoDocument.getListingImageId().isEmpty()){
				TeesEskDocument document=null;
				try {
					document = getDocumentWithResourcesById(nuxeoDocument.getListingImageId(), true);
					
				} catch (Exception e) {
					log.info("Image not found in the nuxeo resource folder for id : "+nuxeoDocument.getListingImageId());
				}
				if(document != null){
					nuxeoDocument.setLocationImageTitle(document.getTitle());
					nuxeoDocument.setLocationImageLink(document.getFileData());
				}
			}
			if(nuxeoDocument.getAddress() != null){
				if(nuxeoDocument.getAddress().getCoordinates() != null && !nuxeoDocument.getAddress().getCoordinates().isEmpty()){
						String coordinates = nuxeoDocument.getAddress().getCoordinates();
						List<String> coordinatesList = Arrays.asList(coordinates.split(","));
						nuxeoDocument.setDataLat(coordinatesList.get(0));
						nuxeoDocument.setDataLng(coordinatesList.get(1));
				}
			}
		}
		request.setAttribute("locationAndContact", documents);
	}
}
