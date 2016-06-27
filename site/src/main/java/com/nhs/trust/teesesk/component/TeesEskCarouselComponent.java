package com.nhs.trust.teesesk.component;

import java.util.ArrayList;
import java.util.List;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.paging.DefaultPagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.trust.teesesk.beans.TeesEskDocument;

public class TeesEskCarouselComponent extends TeesEskComponent{

	public static final Logger log = LoggerFactory.getLogger(TopLevelFoldersComponent.class);

	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) {
		super.doBeforeRender(request, response);

		List<TeesEskDocument> nuxeoDocumentList = new ArrayList<>();
		//TeesEskDocument hippoDocument;

		List<TeesEskDocument> carouselItems = getTeesEskFolderDocuments("Carousel items");
		for(TeesEskDocument hippoDocument : carouselItems){
			//hippoDocument = new TeesEskDocument();
			hippoDocument.setType("carousel");
			nuxeoDocumentList.add(hippoDocument);
		}
		
		request.setAttribute(REQUEST_ATTR_PAGEABLE, new DefaultPagination<>(nuxeoDocumentList));
        request.setAttribute("carouselItems", carouselItems);
	}
}