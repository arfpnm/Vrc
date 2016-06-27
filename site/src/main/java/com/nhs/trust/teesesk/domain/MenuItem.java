package com.nhs.trust.teesesk.domain;

import java.util.ArrayList;

public class MenuItem {

	private String name;
	private String path;
	private ArrayList<MenuItem> subMenuItems;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ArrayList<MenuItem> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(ArrayList<MenuItem> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}
}
