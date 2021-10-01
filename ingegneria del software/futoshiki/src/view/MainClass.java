package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;

import model.ConstrainedMatrixFactory;

public class MainClass {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					InitGui init = new InitGui(new Point(530, 300), new Dimension(300, 150),new ConstrainedMatrixFactory());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
