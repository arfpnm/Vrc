package com.nhs.trust.teesesk.component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.trust.teesesk.beans.TeesEskDocument;
import com.nhs.trust.teesesk.beans.TeesEskDocumentFolder;
import com.nhs.trust.teesesk.domain.Document;


public class TeesEskSideMenuComponent extends TeesEskComponent{

	public static final Logger log = LoggerFactory.getLogger(TeesEskSideMenuComponent.class);

	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) {
		super.doBeforeRender(request, response);
		String parameter=request.getPathInfo();
		String parentPathRef = getParentPath(parameter);
		
		String serviceSelect = getAnyParameter(request, "serviceSelect");
		String locationSelect = getAnyParameter(request, "locationSelect");
		
		TeesEskDocumentFolder teesEskFolderAndChildren = parentPathRef != null ? getAllTeesEskDocumentNavFoldersForPath(parentPathRef) : null;
		
		List<TeesEskDocument> documentList = teesEskFolderAndChildren.getDocuments();
		String parentFolder = "";
		for(TeesEskDocument eskDocument : documentList){
			parentFolder = eskDocument.getPath();
			break;
		}
		Pattern otherPattern = Pattern.compile(".+/(.+)/.+$");
		Matcher matcher = otherPattern.matcher(parentFolder);
		matcher = otherPattern.matcher(parentFolder);
		if (matcher.find()) {
			teesEskFolderAndChildren.setFolderName(matcher.group(1));
		}
		request.setAttribute("teesEskSideMenu", teesEskFolderAndChildren);
		request.setAttribute("serviceSelect", serviceSelect);
		request.setAttribute("locationSelect", locationSelect);
	}
}