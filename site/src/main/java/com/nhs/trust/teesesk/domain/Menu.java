package com.nhs.trust.teesesk.domain;

import java.util.ArrayList;

public class Menu {

	private String title;
	private ArrayList<MenuItem> menuItems;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
}
