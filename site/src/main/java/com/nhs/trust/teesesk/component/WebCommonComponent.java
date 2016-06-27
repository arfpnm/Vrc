package com.nhs.trust.teesesk.component;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhs.trust.teesesk.domain.TeesEskWebsiteCommonAttributes;

public class WebCommonComponent extends TeesEskComponent{

	public static final Logger log = LoggerFactory.getLogger(WebCommonComponent.class);

	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) {
		super.doBeforeRender(request, response);
		TeesEskWebsiteCommonAttributes teesEskWebsiteCommonAttributes = getWebsiteCommonAttributesDocument();
		request.setAttribute("websiteCommonAttributes", teesEskWebsiteCommonAttributes);
	}
}