package com.nhs.trust.teesesk.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.trust.teesesk.beans.TeesEskDocument;
import com.nhs.trust.teesesk.beans.TeesEskDocumentFolder;

public class TeesEskHomePageComponent extends TeesEskComponent {

	public static final Logger log = LoggerFactory.getLogger(TeesEskHomePageComponent.class);

	@Override
	public void doBeforeRender(HstRequest request, HstResponse response) {
		super.doBeforeRender(request, response);
		List<TeesEskDocument> services = getTeesEskFolderDocuments("Care & Treatment");
		request.setAttribute("services", services);
		populateHelpOthers(request, response);
		populateCtaBlocks(request, response);
	}

	private void populateHelpOthers(HstRequest request, HstResponse response) {
		List<TeesEskDocument> ctaBlocks = getTeesEskFolderDocuments("CTA Blocks");
		List<TeesEskDocument> homePageHelp = new ArrayList<>();
		for (TeesEskDocument ctaBlock : ctaBlocks) {
			if (ctaBlock.getOrder() >= 3) {
				homePageHelp.add(ctaBlock);
			}
		}
		request.setAttribute("homePageHelp", homePageHelp);
	}

	private void populateCtaBlocks(HstRequest request, HstResponse response) {
		List<TeesEskDocument> ctaBlocks = getTeesEskFolderDocuments("CTA Blocks");
		List<TeesEskDocument> newCTA = new ArrayList<>();
		for (TeesEskDocument ctaBlock : ctaBlocks) {
			if (ctaBlock.getOrder() <= 2) {
				newCTA.add(ctaBlock);
			}
		}
		request.setAttribute("ctaBlocks", newCTA);
	}

}
