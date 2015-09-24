package com.darkbyte.programs.darkinventory.events;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class NewItemEvent implements Event {

	@Override
	public void run() {
		String name;
		String description;
		int quantity = 0;
		boolean quantitySet = false;
		String type;
		File itemsDirectory = new File("items/");
		File itemFile;
		
		if(!itemsDirectory.exists()) {
			itemsDirectory.mkdir();
		}
		
		name = JOptionPane.showInputDialog(null, "What is the name of the item you would like to create?", "Name", JOptionPane.QUESTION_MESSAGE);
		if(name.equals(JOptionPane.CANCEL_OPTION) || name.equals(JOptionPane.CLOSED_OPTION)) return;
		description = JOptionPane.showInputDialog(null, "What would you like the description for this item to be?", "Description", JOptionPane.QUESTION_MESSAGE);
		if(description.equals(JOptionPane.CANCEL_OPTION) || description.equals(JOptionPane.CLOSED_OPTION)) return;
		while(!quantitySet) {
			try {
				quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "How much of this product should there be?", "Quantity", JOptionPane.QUESTION_MESSAGE));
				quantitySet = true;
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "The quantity must be a whole number!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		type = JOptionPane.showInputDialog(null, "What type of item is this?", "Type", JOptionPane.QUESTION_MESSAGE);
		if(type.equals(JOptionPane.CANCEL_OPTION) || type.equals(JOptionPane.CLOSED_OPTION)) return;
		
		itemFile = new File(itemsDirectory, name + ".darkinventfile");
		
		if(itemFile.exists()) {
			JOptionPane.showMessageDialog(null, "This item already exists!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(itemFile))) {
				itemFile.createNewFile();
				writer.write("name: " + name.replaceAll(" ", "-") + "\n");
				writer.write("description: " + description.replaceAll(" ", "-") + "\n");
				writer.write("quantity: " + new Integer(quantity).toString() + "\n");
				writer.write("type: " + type.replaceAll(" ", "-") + "\n");
			} catch(IOException e) {
				JOptionPane.showMessageDialog(null, "A fatal error occurred whilst creating the file!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
