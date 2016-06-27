package com.nhs.trust.teesesk.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.trust.teesesk.beans.TeesEskDocument;
import com.nhs.trust.teesesk.beans.TeesEskDocumentFolder;

public class TeesEskCareAndTreatmentComponent extends TeesEskComponent {

	public static final Logger log = LoggerFactory.getLogger(TopLevelFoldersComponent.class);

	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) {
		super.doBeforeRender(request, response);
		String parameter=request.getPathInfo();
		//parameter = parameter != null ? parameter.substring(parameter.lastIndexOf("/")+1,parameter.length()) : null;

		//parameter = "/serviceList/Care & Treatment/Adult services";
		parameter = getUrlParameters(parameter);

		if(parameter == null || parameter.isEmpty())
			return;
		
		List<TeesEskDocument> careAndTreatmentSubfolderList = getTeesEskSubfoldersOnly("Care & Treatment");
		List<TeesEskDocument> locationAndContactsSubfolderList = getTeesEskSubfoldersOnly("Locations & Contacts");

		//Add the items to combo - Start
		List<String> services = new ArrayList<String>();
		for(TeesEskDocument eskCareAndTreatment : careAndTreatmentSubfolderList){
			services.add(eskCareAndTreatment.getTitle());
		}
		request.setAttribute("serviceNameList", services);

		List<String> locations = new ArrayList<String>();

		for(TeesEskDocument eskLocationAndContacts : locationAndContactsSubfolderList){
			locations.add(eskLocationAndContacts.getTitle());
		}
		request.setAttribute("locationNameList", locations);


		//If the parameter contains the ID - Start
		//if(parameter != null && !parameter.isEmpty())
		/**
			if(Pattern.compile("(([A-Z-a-z].*[0-9])|([0-9].*[A-Z-a-z]))").matcher(parameter).find()){
				TeesEskDocumentFolder teesEskFolderAndChildren = getAllTeesEskDocumentNavFolders(parameter);
				List<TeesEskDocument> serviceDocuments = teesEskFolderAndChildren != null ? teesEskFolderAndChildren.getDocuments() : null;
				request.setAttribute("allServiceList", serviceDocuments);
			}
			else{
		 **/
		//If the parameter contains the ID - End

		//location and service - search
		/**
		List<TeesEskDocument> careAndTreatmentSubfolderList = getTeesEskSubfoldersOnly("Care & Treatment");
		List<TeesEskDocument> locationAndContactsSubfolderList = getTeesEskSubfoldersOnly("Locations & Contacts");

		//Add the items to combo - Start
		List<String> services = new ArrayList<String>();
		for(TeesEskDocument eskCareAndTreatment : careAndTreatmentSubfolderList){
			services.add(eskCareAndTreatment.getTitle());
		}
		request.setAttribute("serviceNameList", services);

		List<String> locations = new ArrayList<String>();

		for(TeesEskDocument eskLocationAndContacts : locationAndContactsSubfolderList){
			locations.add(eskLocationAndContacts.getTitle());
		}
		request.setAttribute("locationNameList", locations);
		//Add the items to combo - End

		 **/

		//Get the request parameter - Start
		String serviceSelect = getAnyParameter(request, "serviceSelect");
		String locationSelect = getAnyParameter(request, "locationSelect");
		if(serviceSelect != null){
			List<TeesEskDocument> servicesForLocation = new ArrayList<TeesEskDocument>();

			if(locationSelect != null && locationSelect.equalsIgnoreCase("All Locations") && serviceSelect != null && serviceSelect.equalsIgnoreCase("All Services")){
				//Get all services for all locations - make sure that the locations must have been added
				for(TeesEskDocument selectedServiceDoc :  careAndTreatmentSubfolderList){
					TeesEskDocumentFolder careAndTreatmentDocumentFolder = getAllTeesEskDocumentNavFolders(selectedServiceDoc.getDocId());
					List<TeesEskDocument> selectedServices = careAndTreatmentDocumentFolder != null ? careAndTreatmentDocumentFolder.getDocuments() : null;
					if(selectedServices != null){
						for(TeesEskDocument eskDocument : selectedServices){
							servicesForLocation.add(eskDocument);
						}
					}
				}
			}
			else if(locationSelect != null && locationSelect.equalsIgnoreCase("All Locations") && serviceSelect != null && !serviceSelect.equalsIgnoreCase("All Services")){
				List<TeesEskDocument> serviceList = careAndTreatmentSubfolderList.stream().filter(t -> t.getTitle().equalsIgnoreCase(serviceSelect)).collect(Collectors.toList());
				for(TeesEskDocument selectedServiceDoc :  serviceList){
					TeesEskDocumentFolder careAndTreatmentDocumentFolder = getAllTeesEskDocumentNavFolders(selectedServiceDoc.getDocId());
					List<TeesEskDocument> selectedServices = careAndTreatmentDocumentFolder != null ? careAndTreatmentDocumentFolder.getDocuments() : null;
					if(selectedServices != null){
						for(TeesEskDocument eskDocument : selectedServices){
							servicesForLocation.add(eskDocument);
						}
					}
				}
			}
			else if(locationSelect != null && !locationSelect.equalsIgnoreCase("All Locations") && serviceSelect != null && serviceSelect.equalsIgnoreCase("All Services")){
				List<TeesEskDocument> selectedServiceList = careAndTreatmentSubfolderList;
				List<TeesEskDocument> selectedlocationList = locationAndContactsSubfolderList.stream().filter(t -> t.getTitle().equalsIgnoreCase(locationSelect)).collect(Collectors.toList());
				//TeesEskDocument selectedServiceDoc = selectedServiceList != null && selectedServiceList.size() > 0 ? selectedServiceList.get(0) : null;
				TeesEskDocument selectedLocationDoc = selectedlocationList != null && selectedlocationList.size() > 0 ? selectedlocationList.get(0) : null;
				for(TeesEskDocument selectedServiceDoc :  selectedServiceList){
					TeesEskDocumentFolder careAndTreatmentDocumentFolder = getAllTeesEskDocumentNavFolders(selectedServiceDoc.getDocId());
					List<TeesEskDocument> selectedServices = careAndTreatmentDocumentFolder != null ? careAndTreatmentDocumentFolder.getDocuments() : null;
					if(selectedServices != null){
						for(TeesEskDocument eskDocument : selectedServices){
							String[] locationSelector = eskDocument.getLocationSelector();
							if(locationSelector != null){ 
								for(String locationId : locationSelector){
									if(selectedLocationDoc != null){
										if(selectedLocationDoc.getDocId().equalsIgnoreCase(locationId) && selectedLocationDoc.getTitle().equalsIgnoreCase(locationSelect)){
											servicesForLocation.add(eskDocument);
										}
									}
								}	
							}
						}
					}
				}
			}
			else{
				List<TeesEskDocument> selectedServiceList = careAndTreatmentSubfolderList.stream().filter(t -> t.getTitle().equalsIgnoreCase(serviceSelect)).collect(Collectors.toList());
				List<TeesEskDocument> selectedlocationList = locationAndContactsSubfolderList.stream().filter(t -> t.getTitle().equalsIgnoreCase(locationSelect)).collect(Collectors.toList());
				TeesEskDocument selectedServiceDoc = selectedServiceList != null && selectedServiceList.size() > 0 ? selectedServiceList.get(0) : null;
				TeesEskDocument selectedLocationDoc = selectedlocationList != null && selectedlocationList.size() > 0 ? selectedlocationList.get(0) : null;
				if(selectedServiceDoc != null){
					TeesEskDocumentFolder careAndTreatmentDocumentFolder = getAllTeesEskDocumentNavFolders(selectedServiceDoc.getDocId());
					List<TeesEskDocument> selectedServices = careAndTreatmentDocumentFolder != null ? careAndTreatmentDocumentFolder.getDocuments() : null;
					if(selectedServices != null){
						for(TeesEskDocument eskDocument : selectedServices){
							String[] locationSelector = eskDocument.getLocationSelector();
							if(locationSelector != null){ 
								for(String locationId : locationSelector){
									if(selectedLocationDoc != null){
										if(selectedLocationDoc.getDocId().equalsIgnoreCase(locationId) && selectedLocationDoc.getTitle().equalsIgnoreCase(locationSelect)){
											servicesForLocation.add(eskDocument);
										}
									}
								}	
							}
						}
					}
				}
			}
			request.setAttribute("servicesForLocation", servicesForLocation);
		}
		else{
			TeesEskDocumentFolder teesEskFolderAndChildren = getAllTeesEskDocumentNavFoldersForPath(parameter);
			List<TeesEskDocument> serviceDocuments = teesEskFolderAndChildren != null ? teesEskFolderAndChildren.getDocuments() : null;
			request.setAttribute("servicesForLocation", serviceDocuments);
		}
		request.setAttribute("serviceSelect", serviceSelect);
		request.setAttribute("locationSelect", locationSelect);
	}

}
