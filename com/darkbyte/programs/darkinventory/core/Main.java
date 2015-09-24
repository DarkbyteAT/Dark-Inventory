package com.darkbyte.programs.darkinventory.core;

import com.darkbyte.programs.darkinventory.display.DarkButton;
import com.darkbyte.programs.darkinventory.display.Window;
import com.darkbyte.programs.darkinventory.events.*;

public class Main {
	public static void main(String[] args) {
		Window window = new Window(1280, 720, "DarkInventory - Menu", true, Window.EXIT_ON_CLOSE, true);
		DarkButton newButton = new DarkButton("New", new NewItemEvent());
		DarkButton searchButton = new DarkButton("Search", new SearchItemEvent());
		DarkButton editButton = new DarkButton("Edit", new EditItemEvent());
		DarkButton listButton = new DarkButton("List Items", new ListItemEvent());
		
		window.add(newButton);
		window.add(searchButton);
		window.add(editButton);
		window.add(listButton);
	}
}
