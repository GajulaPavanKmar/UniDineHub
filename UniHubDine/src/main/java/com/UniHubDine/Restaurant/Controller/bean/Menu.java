package com.UniHubDine.Restaurant.Controller.bean;

public class Menu {
	    private int menu_id;
	    private String name;
	    private String description;
	    private String image;
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public int getId() {
			return menu_id;
		}
		public void setId(int id) {
			this.menu_id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		@Override
		public String toString() {
			return "Menu [id=" + menu_id + ", name=" + name + ", description=" + description + ", image=" + image + "]";
		}
		

}
