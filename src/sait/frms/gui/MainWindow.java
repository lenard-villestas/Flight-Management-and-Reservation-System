package sait.frms.gui;
/**
 * Holds the main window of our application
 * <p>Holds nav bar for switching between flight and reservation tab.
 * @author Lenard
 * @version March 23, 2022
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;

/**
 * The main window (JFrame).
 * 
 */
public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TAB_FLIGHTS = "flights";
	private static final String TAB_RESERVATIONS = "reservations";

	/**
	 * Holds the flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Holds the reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * Card layout to display tab content.
	 */
	private CardLayout cardLayout;

	/**
	 * North panel.
	 */
	private JPanel northPanel;

	/**
	 * Center panel.
	 */
	private JPanel centerPanel;

	/**
	 * Flights tab button.
	 */
	private JButton flightsButton;

	/**
	 * Reservations tab button.
	 */
	private JButton reservationsButton;

	/**
	 * Flights tab panel.
	 */
	private TabBase flightsTab;

	/**
	 * Reservations tab panel.
	 */
	private TabBase reservationsTab;

	/**
	 * Creates the Main Window and any components inside it.
	 */
	public MainWindow() {
		this.flightManager = new FlightManager();

		this.reservationManager = new ReservationManager();

		setTitle("Flight Reservation Management System");

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		northPanel = createNorthPanel();
		add(northPanel, BorderLayout.NORTH);

		centerPanel = createCenterPanel();
		add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		JPanel tabPanel = createTabPanel();
		panel.add(tabPanel, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		cardLayout = new CardLayout();

		this.flightsTab = new FlightsTab(this.flightManager, this.reservationManager);
		this.reservationsTab = new ReservationsTab(this.reservationManager);

		panel.setLayout(cardLayout);

		panel.add(flightsTab.getPanel(), TAB_FLIGHTS);
		panel.add(reservationsTab.getPanel(), TAB_RESERVATIONS);

		cardLayout.first(panel);

		return panel;
	}

	/**
	 * Creates the tab buttons.
	 * 
	 * @return JPanel containing tab buttons.
	 */
	private JPanel createTabPanel() {
		JPanel tabPanel = new JPanel();

		tabPanel.setLayout(new GridLayout(1, 2));

		flightsButton = new JButton("Flights");
		reservationsButton = new JButton("Reservations");

		flightsButton.addActionListener(new TabButtonActionListener());
		reservationsButton.addActionListener(new TabButtonActionListener());

		tabPanel.add(flightsButton);
		tabPanel.add(reservationsButton);

		return tabPanel;
	}

	/**
	 * Displays the JFrame window.
	 */
	public void display() {
		pack();
		setVisible(true);
	}

	/**
	 * Inner action listener class that listens for a tab button to be clicked.
	 * <p>refreshes reservation list when reserve tab is shwon
	 * 
	 * @author Nick Hamnett, Mohamed, Lenard Villestas
	 * @version March 23, 2022
	 */
	private class TabButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == flightsButton) {
				cardLayout.show(centerPanel, TAB_FLIGHTS);
			} else if (e.getSource() == reservationsButton) {
				cardLayout.show(centerPanel, TAB_RESERVATIONS);
				((ReservationsTab) reservationsTab).refreshList(null,null,null);
			}
		}

	}
}
