package com.darkbyte.programs.darkinventory.events;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.darkbyte.programs.darkinventory.display.DarkButton;
import com.darkbyte.programs.darkinventory.display.Window;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class EditItemEvent implements Event {

	@Override
	public void run() {
		String name;
		File itemFile;
		
		name = JOptionPane.showInputDialog(null, "Please enter the name of the item you would like to edit", "Enter", JOptionPane.QUESTION_MESSAGE);
		itemFile = new File("items/" + name + ".darkinventfile");
		if(!itemFile.exists()) {
			JOptionPane.showMessageDialog(null, "That item doesn't exist!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Window dataWindow = new Window(500, 500, "DarkInventory - " + name, true, Window.DISPOSE_ON_CLOSE, false);
			JTextArea dataArea = new JTextArea();
			dataArea.setEditable(false);
			dataWindow.add(new JScrollPane(dataArea));
			try {
				dataArea.setText(Files.toString(itemFile, Charsets.UTF_8).replaceAll("-", " "));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An error occurred whilst trying to read the item's data!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			EditItemEventEditDescriptionEvent e1 = new EditItemEventEditDescriptionEvent();
			e1.setCurrentFile(itemFile);
			DarkButton editDescriptionButton = new DarkButton("Edit Description", e1);
			EditItemEventEditQuantityEvent e2 = new EditItemEventEditQuantityEvent();
			e2.setCurrentFile(itemFile);
			DarkButton editQuantityButton = new DarkButton("Edit Quantity", e2);
			EditItemEventEditTypeEvent e3 = new EditItemEventEditTypeEvent();
			e3.setCurrentFile(itemFile);
			DarkButton editTypeButton = new DarkButton("Edit Type", e3);
			
			dataWindow.add(editDescriptionButton);
			dataWindow.add(editQuantityButton);
			dataWindow.add(editTypeButton);
		}
	}
}

class EditItemEventEditDescriptionEvent implements Event {
	
	private File currentFile;
	private Scanner scanner;
	
	@Override
	public void run() {
		int currentPosition = 0;
		String name = null;
		String newDescription = JOptionPane.showInputDialog(null, "Please enter the new description of the item", "Enter", JOptionPane.QUESTION_MESSAGE);
		int quantity = 0;
		String type = null;
		String tempVar;
		
		while(scanner.hasNext()) {
			tempVar = scanner.nextLine();
			
			if(currentPosition == 1) {
				name = tempVar;
			} else if(currentPosition == 5) {
				quantity = Integer.parseInt(tempVar);
			} else if(currentPosition == 7) {
				type = tempVar;
			}
			
			currentPosition++;
		}
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
			writer.write("name: " + name.replaceAll(" ", "-") + "\n");
			writer.write("description: " + newDescription.replaceAll(" ", "-") + "\n");
			writer.write("quantity: " + quantity + "\n");
			writer.write("type: " + type.replaceAll(" ", "-") + "\n");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occurred whilst trying to change the item's description!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
		try {
			this.scanner = new Scanner(currentFile);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occurred whilst trying to change the item's description!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

class EditItemEventEditQuantityEvent implements Event {
	
	private File currentFile;
	private Scanner scanner;
	
	@Override
	public void run() {
		int currentPosition = 0;
		String name = null;
		String description = null;
		int newQuantity = 0;
		String type = null;
		String tempVar;
		boolean quantityIsSet = false;
		
		while(!quantityIsSet) {
			try {
				newQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the new quantity of the item", "Enter", JOptionPane.QUESTION_MESSAGE));
				quantityIsSet = true;
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "The quantity must be a whole number!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		while(scanner.hasNext()) {
			tempVar = scanner.nextLine();
			
			if(currentPosition == 1) {
				name = tempVar;
			} else if(currentPosition == 3) {
				description = tempVar;
			} else if(currentPosition == 7) {
				type = tempVar;
			}
			
			currentPosition++;
		}
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
			writer.write("name: " + name.replaceAll(" ", "-") + "\n");
			writer.write("description: " + description.replaceAll(" ", "-") + "\n");
			writer.write("quantity: " + newQuantity + "\n");
			writer.write("type: " + type.replaceAll(" ", "-") + "\n");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occurred whilst trying to change the item's quantity!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
		try {
			this.scanner = new Scanner(currentFile);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occurred whilst trying to change the item's quantity!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

class EditItemEventEditTypeEvent implements Event {
	
	private File currentFile;
	private Scanner scanner;
	
	@Override
	public void run() {
		int currentPosition = 0;
		String name = null;
		String description = null;
		int quantity = 0;
		String newType = JOptionPane.showInputDialog(null, "Please enter the new type of the item", "Enter", JOptionPane.QUESTION_MESSAGE);
		String tempVar;
		
		while(scanner.hasNext()) {
			tempVar = scanner.nextLine();
			
			if(currentPosition == 1) {
				name = tempVar;
			} else if(currentPosition == 3) {
				description = tempVar;
			} else if(currentPosition == 5) {
				quantity = Integer.parseInt(tempVar);
			}
			
			currentPosition++;
		}
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
			writer.write("name: " + name + "\n");
			writer.write("description: " + description + "\n");
			writer.write("quantity: " + quantity + "\n");
			writer.write("type: " + newType + "\n");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occurred whilst trying to change the item's type!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
		try {
			this.scanner = new Scanner(currentFile);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occurred whilst trying to change the item's type!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
