package com.nhs.trust.teesesk.component;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.trust.teesesk.beans.TeesEskDocumentFolder;

public class TeesEskLatestNewsComponent extends TeesEskComponent {

	public static final Logger log = LoggerFactory.getLogger(TopLevelFoldersComponent.class);

	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) {
		super.doBeforeRender(request, response);
		String parameter = request.getPathInfo();
		parameter = getUrlParameters(parameter);
		TeesEskDocumentFolder latestNews = getAllTeesEskDocumentNavFoldersForPath(parameter);
		request.setAttribute("latestNews", latestNews);
	}

}
