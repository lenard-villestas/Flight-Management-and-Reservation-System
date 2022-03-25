
package sait.frms.gui;
import java.awt.*;

/**
 * This class holds the GUI components for our reservations tab
 * <p> Panel for updating a reservation, finding a reservation, reservations list view
 * @author Lenard V, Javaria K, Patrick O
 * @version March 23, 2022
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frms.exception.*;
import sait.frms.manager.*;
import sait.frms.problemdomain.*;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager = new ReservationManager();

	private JList<Reservation> reservationsList;
	private DefaultListModel<Reservation> reservationsModel;

	/**
	 * variables to find reservation
	 */
	ArrayList<Reservation> reservationsArray = reservationManager.findReservations(null, null, null);
	private String code;
	private String airline;
	private String name;
	JButton updateButton = new JButton("Update");
	JButton searchButton = new JButton("Find Reservations");
	JTextField nameSearchField = new JTextField(10);
	JTextField airlineSearchField = new JTextField();
	JTextField codeSearchField = new JTextField();
	/**
	 * variables for selected input
	 */
	// will hold selected reservation from JList
	Reservation selectedReservation = new Reservation();
	JTextField codeField = new JTextField();
	JTextField flightField = new JTextField();
	JTextField airlineField = new JTextField();
	JTextField costField = new JTextField();
	JTextField nameField = new JTextField(10);
	JTextField citizenshipField = new JTextField(10);
	String[] statusOption = { "Active", "Inactive" };
	JComboBox statusBox = new JComboBox(statusOption);

	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel westPanel = createWestPanel();
		panel.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);

	}

	/**
	 * Creates the south panel.
	 * 
	 * @return JPanel that goes in south.
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		// search title
		JLabel searchTitle = new JLabel("Search", SwingConstants.CENTER);
		searchTitle.setFont(new Font("serif", Font.PLAIN, 27));
		c.gridx = 1;
		c.gridy = 0;
		panel.add(searchTitle, c);

		// code field
		JLabel codeLabel = new JLabel("Code: ");
		codeLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 0;
		panel.add(codeLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		codeSearchField.setPreferredSize(new Dimension(550, 20));
		panel.add(codeSearchField, c);

		// airline field
		JLabel airlineLabel = new JLabel("Airline: ");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = 0;
		panel.add(airlineLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		airlineSearchField.setPreferredSize(new Dimension(550, 20));
		panel.add(airlineSearchField, c);

		// name field
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		c.ipady = 0;
		panel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		nameSearchField.setPreferredSize(new Dimension(550, 20));
		panel.add(nameSearchField, c);

		// search button
		searchButton.addActionListener(new ButtonListener());
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		panel.add(searchButton, c);

		return panel;
	}

	/**
	 * Creates the east panel.
	 * 
	 * @return JPanel that goes in east.
	 */
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		// title of reservation
		JLabel resTitle = new JLabel("Reserve", SwingConstants.CENTER);
		resTitle.setFont(new Font("serif", Font.PLAIN, 27));
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 50;
		panel.add(resTitle, c);

		// code field
		JLabel codeLabel = new JLabel("Code: ");
		codeLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 0;
		panel.add(codeLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		codeField.setPreferredSize(new Dimension(35, 20));
		codeField.setEditable(false);
		panel.add(codeField, c);

		// flight field
		JLabel flightLabel = new JLabel("Flight: ");
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = 0;
		panel.add(flightLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		flightField.setPreferredSize(new Dimension(35, 20));
		flightField.setEditable(false);
		panel.add(flightField, c);

		// airline field
		JLabel airlineLabel = new JLabel("Airline: ");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);

		c.gridx = 0;
		c.gridy = 3;
		c.ipady = 0;
		panel.add(airlineLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		airlineField.setPreferredSize(new Dimension(35, 20));
		airlineField.setEditable(false);
		panel.add(airlineField, c);

		// cost field
		JLabel costLabel = new JLabel("Cost: ");
		costLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		c.ipady = 0;
		panel.add(costLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		costField.setPreferredSize(new Dimension(35, 20));
		costField.setEditable(false);
		panel.add(costField, c);

		// name field
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 5;
		c.ipady = 0;
		panel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 5;
		panel.add(nameField, c);

		// citizenship field
		JLabel citizenshipLabel = new JLabel("Citizenship: ");
		citizenshipLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 6;
		c.ipady = 0;
		panel.add(citizenshipLabel, c);

		c.gridx = 1;
		c.gridy = 6;
		panel.add(citizenshipField, c);

		// status field
		JLabel statusLabel = new JLabel("Status: ");
		statusLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 7;
		c.ipady = 0;
		panel.add(statusLabel, c);

		c.gridx = 1;
		c.gridy = 7;
		panel.add(statusBox, c);

		// update button
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(30, 0, 0, 0);
		c.gridwidth = 2;
		c.weightx = 1;

		updateButton.addActionListener(new ButtonListener());
		updateButton.setPreferredSize(new Dimension(140, 25));
		panel.add(updateButton, c);

		panel.setPreferredSize(new Dimension(200, 100));

		return panel;
	}

	/**
	 * Creates the west panel.
	 * 
	 * @return JPanel that goes in west.
	 */
	private JPanel createWestPanel() {

		JPanel panel = new JPanel();
		reservationsModel = new DefaultListModel<>();
		for (int i = 0; i < reservationsArray.size(); i++) {
			reservationsModel.addElement(reservationsArray.get(i));
		}
		reservationsList = new JList<>(reservationsModel);
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		scrollPane.setPreferredSize(new Dimension(400, 200));

		reservationsList.addListSelectionListener(new MyListSelectionListener());
		panel.add(scrollPane);

		return panel;
	}

	/**
	 * This is a class listener for JList reservationsList
	 * 
	 * @implements ListSelectionListener
	 * @Lenard
	 * @version March 23, 2022
	 */
	private class MyListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {

			if (!e.getValueIsAdjusting()) {
				// getting the object from our JList, (Alot of casting was needed here)
				JList source = (JList) e.getSource();
				DefaultListModel model = (DefaultListModel) source.getModel();
				int index = source.getSelectedIndex();
				Reservation reservation = (Reservation) model.getElementAt(index);

				// getting selected flight
				selectedReservation = reservation;

				// setting value to be shown in reserve Text fields
				nameField.setText(selectedReservation.getName());
				citizenshipField.setText(selectedReservation.getCitizenship());
				codeField.setText(selectedReservation.getCode());
				flightField.setText(selectedReservation.getFlightCode());
				airlineField.setText(selectedReservation.getAirline());
				costField.setText(String.format("%,.2f", selectedReservation.getCost()));

				int status = 1;
				if (selectedReservation.isActive()) {
					status = 0;
				}
				statusBox.setSelectedIndex(status);

			}
		}

	}

	/**
	 * Button listener class for updating and finding reservations
	 * 
	 * @author Lenard
	 * @version March 23, 2022
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == updateButton) {

				try {
					// this will trigger modify reservation

					// JOptionPane.showMessageDialog(null, "Not yet implemented");

					// Reservation(String code, String flightCode, String airline, String name,
					// String citizenship, double cost, boolean active)

					boolean isActive = false;

					if (statusBox.getSelectedItem().toString().equalsIgnoreCase("Active")) {
						isActive = true;
					}
					// check if name or citizenship is blank
					if (nameField.getText().isEmpty() || citizenshipField.getText().isEmpty()) {
						throw new InvalidFieldException();
					}
					// parse cost field
					String removedCostComma = costField.getText();
					removedCostComma = removedCostComma.replaceAll(",", "");
					double costFieldAsDouble = Double.parseDouble(removedCostComma);
					Reservation newReservation = new Reservation(codeField.getText(), flightField.getText(),
							airlineField.getText(), nameField.getText(), citizenshipField.getText(), costFieldAsDouble,
							isActive);
					reservationManager.updateReservation(newReservation);
					// add seat to flight if reservation set to inactive
					if (!isActive) {
						FlightManager flightManager = new FlightManager();
						Flight flightByCode = flightManager.findFlightByCode(newReservation.getFlightCode());
						flightByCode.setSeats(flightByCode.getSeats() + 1);
						JOptionPane.showMessageDialog(null,
								"Reservation :" + newReservation.getCode() + " has been updated and detected inactive,\n seat slot for this reservation is or remains unreclaimed");
					} else {
						JOptionPane.showMessageDialog(null,
								"Reservation :" + newReservation.getCode() + " has been updated!");
					}
					// refresh search the code
					refreshList(null, null, null);

					/*
					 * reservationsArray = reservationManager.findReservations(null, null, null);
					 * reservationsModel = new DefaultListModel<Reservation>(); for (int i = 0; i <
					 * reservationsArray.size(); i++) {
					 * reservationsModel.addElement(reservationsArray.get(i)); }
					 * reservationsList.setModel(reservationsModel);
					 */

				} catch (InvalidFieldException efEx) {
					JOptionPane.showMessageDialog(null, "Name or Citizenship cannot be empty, Please try again.");
				} catch (NullPointerException npe) {
					System.out.println("NUll");
				}

			} else if (e.getSource() == searchButton) {
				code = codeSearchField.getText();
				airline = airlineSearchField.getText();
				name = nameSearchField.getText();

				if (code.isEmpty()) {
					code = null;
				}
				if (airline.isEmpty()) {
					airline = null;
				}
				if (name.isEmpty()) {
					name = null;
				}

				refreshList(code, airline, name);
				/*
				 * try { reservationsArray = reservationManager.findReservations(code, airline,
				 * name); reservationsModel = new DefaultListModel<Reservation>(); for (int i =
				 * 0; i < reservationsArray.size(); i++) {
				 * reservationsModel.addElement(reservationsArray.get(i)); }
				 * reservationsList.setModel(reservationsModel); } catch
				 * (ArrayIndexOutOfBoundsException iob) { //not sure why this triggers on rare
				 * occasions when testing but it doesnt break the program }
				 */
			}
		}

	}
	
	/**
	 * This method has the sole purpose of refreshing the reservations list
	 * @param searchCode
	 * @param searchAirline
	 * @param searchName
	 * 
	 * @author Lenard
	 * @version March 23, 2022
	 */
	public void refreshList(String searchCode, String searchAirline, String searchName) {

		try {
			reservationsArray = reservationManager.findReservations(searchCode, searchAirline, searchName);
			reservationsModel = new DefaultListModel<Reservation>();
			for (int i = 0; i < reservationsArray.size(); i++) {
				reservationsModel.addElement(reservationsArray.get(i));
			}
			reservationsList.setModel(reservationsModel);
		} catch (ArrayIndexOutOfBoundsException iob) {
			// not sure why this triggers on rare occasions when testing but it doesnt break
			// the program
		}
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}
}
