package com.darkbyte.programs.darkinventory.events;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.darkbyte.programs.darkinventory.display.Window;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class SearchItemEvent implements Event {

	@Override
	public void run() {
		boolean nameIsSelected = false;
		String name = null;
		File itemFile = null;
		
		while(!nameIsSelected) {
			name = JOptionPane.showInputDialog(null, "Please enter the name of the file you would like to search for", "Enter", JOptionPane.QUESTION_MESSAGE);
			itemFile = new File("items/" + name + ".darkinventfile");
			if(!itemFile.exists()) {
				if(!(name.equals(JOptionPane.CLOSED_OPTION) || name.equals(JOptionPane.CLOSED_OPTION))) {
					JOptionPane.showMessageDialog(null, "That's not a valid item name!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					return;
				}
			} else if(name.equals(JOptionPane.CANCEL_OPTION) || name.equals(JOptionPane.CLOSED_OPTION)){
				return;
			} else {
				nameIsSelected = true;
			}
		}
		
		int option = JOptionPane.showOptionDialog(null, "Would you like to see data on the selected item?", "View Data", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(option == JOptionPane.YES_OPTION) {
			Window dataWindow = new Window(500, 500, "DarkInventory - " + name, true, Window.DISPOSE_ON_CLOSE, false);
			JTextArea dataArea = new JTextArea();
			dataArea.setEditable(false);
			dataWindow.add(new JScrollPane(dataArea));
			try {
				dataArea.setText(Files.toString(itemFile, Charsets.UTF_8).replaceAll("-", " "));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An error occurred whilst trying to read the item's data!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			int optionSet2 = JOptionPane.showOptionDialog(null, "Would you like to delete the selected item?", "Delete", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			
			if(optionSet2 == JOptionPane.YES_OPTION) {
				itemFile.delete();
				JOptionPane.showMessageDialog(null, "The item '" + name + "' was deleted!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
