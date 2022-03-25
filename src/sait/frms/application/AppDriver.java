/**
 * This class contains our void main
 * @version 1
 */
package sait.frms.application;

import sait.frms.manager.FlightManager;
import sait.frms.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		MainWindow mainWindow = new MainWindow();
		mainWindow.display();

		/*
		 * FlightManager fm = new FlightManager(); fm.test();
		 */
	}
}
