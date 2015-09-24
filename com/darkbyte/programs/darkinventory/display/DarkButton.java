package com.darkbyte.programs.darkinventory.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.darkbyte.programs.darkinventory.events.Event;

public class DarkButton extends JButton {
	private static final long serialVersionUID = 1L;
	
	public DarkButton(String name, final Event e) {
		super(name);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e1) {
				e.run();
			}
		});
	}
}
