package com.nhs.trust.teesesk.component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.trust.teesesk.beans.TeesEskDocument;
import com.nhs.trust.teesesk.beans.TeesEskDocumentFolder;

public class TeesEskHomePageLatestNewsComponent extends TeesEskComponent {

	public static final Logger log = LoggerFactory.getLogger(TeesEskHomePageLatestNewsComponent.class);

	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) {
		super.doBeforeRender(request, response);
		TeesEskDocumentFolder latestNews = getAllTeesEskDocumentNavFoldersForPath("About/Trust News/Latest News");
		List<TeesEskDocument> homepageNews = latestNews.getDocuments();
		Collections.sort(homepageNews, new Comparator<TeesEskDocument>() {
			@Override
			public int compare(TeesEskDocument o1, TeesEskDocument o2) {
				return o2.getLastModified().compareTo(o1.getLastModified());
			}

		});
		request.setAttribute("homepageNews", homepageNews);
	}

}
