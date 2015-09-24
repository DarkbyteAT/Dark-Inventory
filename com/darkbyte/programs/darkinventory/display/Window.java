package com.darkbyte.programs.darkinventory.display;

import java.awt.*;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, boolean resizable, int closeOperation, boolean maximized) {
		super(title);
		setSize(width, height);
		setResizable(resizable);
		setDefaultCloseOperation(closeOperation);
		if(maximized) setExtendedState(MAXIMIZED_BOTH);
		setLayout(new FlowLayout());
		setVisible(true);
		getContentPane().setBackground(Color.BLACK);
	}
}
