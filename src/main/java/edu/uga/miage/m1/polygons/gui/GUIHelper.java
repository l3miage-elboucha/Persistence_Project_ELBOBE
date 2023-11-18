package edu.uga.miage.m1.polygons.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *  @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class GUIHelper {
	
	private GUIHelper(){
	}

	public static void showOnFrame(TheClient client) {
		WindowAdapter wa = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		client.getFrame().addWindowListener(wa);
		client.getFrame().pack();
		client.getFrame().setVisible(true);
	}

}