package com.nhs.trust.teesesk.component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.trust.teesesk.beans.TeesEskDocumentFolder;

public class TeesEskNewsComponent extends TeesEskComponent {

	public static final Logger log = LoggerFactory.getLogger(TopLevelFoldersComponent.class);

	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) {
		super.doBeforeRender(request, response);
		String parameter = request.getPathInfo();
		parameter = getUrlParameters(parameter);
		TeesEskDocumentFolder news = getAllTeesEskDocumentNavFoldersForPath(parameter);
		List<TeesEskDocumentFolder> subFolders = news.getSubfolder();
		if (subFolders != null) {
			Collections.sort(subFolders, new Comparator<TeesEskDocumentFolder>() {
				@Override
				public int compare(TeesEskDocumentFolder o1, TeesEskDocumentFolder o2) {
					return o1.getOrder().compareTo(o2.getOrder());
				}

			});
		}
		request.setAttribute("news", subFolders);
	}

}
