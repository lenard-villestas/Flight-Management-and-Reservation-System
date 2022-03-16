/**
 * Author: Javaria Khan
 */
package sait.frms.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	private JList<Reservation> reservationsList;
	private DefaultListModel<Reservation> reservationsModel;
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
		
		JTextField codeField = new JTextField();
		c.gridx = 1;
		c.gridy = 1;
		codeField.setPreferredSize(new Dimension(550, 20));
		panel.add(codeField, c);
		
		
		// airline field 
		JLabel airlineLabel = new JLabel("Airline: ");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = 0;
		panel.add(airlineLabel, c);
		
		JTextField airlineField = new JTextField();
		c.gridx = 1;
		c.gridy = 2;
		airlineField.setPreferredSize(new Dimension(550, 20));
		panel.add(airlineField, c);
		
		// name field 
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		c.ipady = 0;
		panel.add(nameLabel, c);
		
		JTextField nameField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 3;
		nameField.setPreferredSize(new Dimension(550, 20));
		panel.add(nameField, c);
		
		// search button 
		JButton searchButton = new JButton("Find Reservations");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		panel.add(searchButton, c);
		
		return panel;
	}

	/**
	 * Creates the east panel.
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
		
		JTextField codeField = new JTextField();
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
		
		JTextField flightField = new JTextField();
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
		
		JTextField airlineField = new JTextField();
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
		
		JTextField costField = new JTextField();
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
		
		JTextField nameField = new JTextField(10);
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
		
		JTextField citizenshipField = new JTextField(10);
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
		
		String[] statusOption = { "Active", "Inactive" };
		JComboBox statusBox = new JComboBox(statusOption);
		c.gridx = 1;
		c.gridy = 7;
		panel.add(statusBox, c);
		
		// update button 
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(30, 0, 0, 0);
		c.gridwidth = 2;
		c.weightx = 1;
		JButton updateButton = new JButton("Update");
		updateButton.setPreferredSize(new Dimension(140, 25));
		panel.add(updateButton, c);
		
		panel.setPreferredSize(new Dimension(200, 100));
		
		return panel;
	}

	/**
	 * Creates the west panel.
	 * @return JPanel that goes in west.
	 */
	private JPanel createWestPanel() {
		
		JPanel panel = new JPanel();
		reservationsModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationsModel);
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		panel.add(scrollPane);

		
		
		return panel;
	}
	/**
	 * This is a class listener for JList reservationsList
	 * @implements ListSelectionListener
	 *
	 */
	private class MyListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
}
