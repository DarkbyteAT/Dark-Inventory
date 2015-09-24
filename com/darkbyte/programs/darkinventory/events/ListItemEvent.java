package com.darkbyte.programs.darkinventory.events;

import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.darkbyte.programs.darkinventory.display.Window;

public class ListItemEvent implements Event {

	@Override
	public void run() {
		File itemDirectory = new File("items/");
		File[] filesInDirectory = null;
		
		if(!itemDirectory.exists()) {
			itemDirectory.mkdir();
		} else {
			filesInDirectory = itemDirectory.listFiles();
		}
		
		Window dataWindow = new Window(500, 500, "DarkInventory - Items", true, Window.DISPOSE_ON_CLOSE, false);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		for(File file : filesInDirectory) {
			String filename = file.getName();
			textArea.setText(textArea.getText() + filename.replaceAll(".darkinventfile", "\n"));
		}
		
		dataWindow.add(new JScrollPane(textArea));
	}
}
