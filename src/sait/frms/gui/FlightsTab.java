package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Set;

import javax.print.attribute.standard.JobPriority;
import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 * @author Lenard
 */
public class FlightsTab extends TabBase {
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager = new FlightManager();

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;
	private DefaultListModel<Flight> flightsModel;

	/**
	 * arrays to be used by flight finder comboboxed
	 */
	private ArrayList<String> flightAirportList = flightManager.getAirports();
	private String[] flightDateList = { "ANY", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY",
			"SATURDAY" };
	
	/**
	 * JTexFields used in Reserve Panel
	 */
	//they need to be instance variable so we can modify them later on from other methods
	JTextField flightJTextField = new JTextField(20); // 20 to set the width
	JTextField airlineJTextField = new JTextField();
	JTextField dayJTextField = new JTextField();
	JTextField timeJTextField = new JTextField();
	JTextField costJTextField = new JTextField();
	JTextField nameJTextField = new JTextField();
	JTextField citizenshipJTextField = new JTextField();
	
	/**
	 * variables that will be used to make a reservation
	 */
	private String flightCode;
	private String airlineName;
	private String day;
	private String time;
	private double cost;
	private String name;
	private String citizenship;

	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		/*
		 * Flight List Box will be added inside centerJPanel
		 * -----------------------------------------------------------------------------
		 * ----------
		 */
		ArrayList<Flight> flightsArray = flightManager.getFlights();
		// converting flightlist array to a modellist to be used by our JList
		flightsModel = new DefaultListModel<Flight>();
		for (int i = 0; i < flightsArray.size(); i++) {
			flightsModel.addElement(flightsArray.get(i));
		}
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// flightsList.setPreferredSize(new Dimension(600, 400));

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		flightsList.addListSelectionListener(new MyListSelectionListener());

		/*
		 * Reservation Panel will be added inside centerJPanel
		 * -----------------------------------------------------------------------------
		 * ---- this is a border layout panel with a nested gridbag panel which has a
		 * nested (2) gridlayout panels
		 */

		JPanel reservePanel = new JPanel();
		reservePanel.setLayout(new BorderLayout(20, 20));

		// Title
		JLabel reserveTitleJLabel = new JLabel("Reserve", SwingConstants.CENTER);
		reserveTitleJLabel.setFont(new Font("serif", Font.PLAIN, 26));

		// Making the gridbag panel which will hold the grid panels with textfield and
		// labels
		JPanel formFieldJPanel = new JPanel();
		GridBagLayout gblFormField = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		formFieldJPanel.setLayout(gblFormField);

		// making the 2 grid panels for labels and textfields
		JPanel labelsJPanel = new JPanel();
		JPanel textFieldsJPanel = new JPanel();
		labelsJPanel.setLayout(new GridLayout(7, 1, 0, 4));// 7 columns, 1 row, 0 horizontal gap, 4px vertical gap
		textFieldsJPanel.setLayout(new GridLayout(7, 1));

		// making labels
		JLabel flightJLabel = new JLabel("Flight:", SwingConstants.RIGHT);
		JLabel airlineJLabel = new JLabel("Airline:", SwingConstants.RIGHT);
		JLabel dayJLabel = new JLabel("Day:", SwingConstants.RIGHT);
		JLabel timeJLabel = new JLabel("Time:", SwingConstants.RIGHT);
		JLabel costJLabel = new JLabel("Cost:", SwingConstants.RIGHT);
		JLabel nameJLabel = new JLabel("Name:", SwingConstants.RIGHT);
		JLabel citizenshipJLabel = new JLabel("Citizenship:", SwingConstants.RIGHT);

		// making textfields

		flightJTextField.setEditable(false); // make the textfield a read only

		airlineJTextField.setEditable(false);

		dayJTextField.setEditable(false);

		timeJTextField.setEditable(false);

		costJTextField.setEditable(false);

		
		// adding labels and textfields to their grid panels
		labelsJPanel.add(flightJLabel);
		textFieldsJPanel.add(flightJTextField);

		labelsJPanel.add(airlineJLabel);
		textFieldsJPanel.add(airlineJTextField);

		labelsJPanel.add(dayJLabel);
		textFieldsJPanel.add(dayJTextField);

		labelsJPanel.add(timeJLabel);
		textFieldsJPanel.add(timeJTextField);

		labelsJPanel.add(costJLabel);
		textFieldsJPanel.add(costJTextField);

		labelsJPanel.add(nameJLabel);
		textFieldsJPanel.add(nameJTextField);

		labelsJPanel.add(citizenshipJLabel);
		textFieldsJPanel.add(citizenshipJTextField);

		// now I will add these 2 grid panels in my gridbag with constraints

		gbc1.gridx = 0;
		gbc1.gridy = 0;
		gbc1.gridheight = 1;
		gbc1.gridwidth = 1;
		gblFormField.setConstraints(labelsJPanel, gbc1);
		formFieldJPanel.add(labelsJPanel);
		gbc1.gridx = 1;
		gbc1.gridy = 0;
		gbc1.gridheight = 1;
		gbc1.gridwidth = 3;
		gblFormField.setConstraints(textFieldsJPanel, gbc1);
		formFieldJPanel.add(textFieldsJPanel);

		// Button
		JButton reserveButton = new JButton("Reserve");
		reserveButton.addActionListener(new ButtonListener());

		// add reserve panel components

		reservePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 0)); // these are to add bordermargins
		reservePanel.add(reserveTitleJLabel, BorderLayout.NORTH);
		reservePanel.add(formFieldJPanel, BorderLayout.CENTER);
		reservePanel.add(reserveButton, BorderLayout.SOUTH);

		/*
		 * Flight Finder Panel at the south
		 * -----------------------------------------------------------------------------
		 * ----
		 */
		// layout setup
		JPanel flightFinderPanel = new JPanel();
		flightFinderPanel.setLayout(new BorderLayout());

		// adding title for NORTH
		JLabel finderTitleJLabel = new JLabel("Flight Finder", SwingConstants.CENTER);
		finderTitleJLabel.setFont(new Font("serif", Font.PLAIN, 26));

		// adding button for SOUTH
		JButton findButton = new JButton("Find Flights");
		findButton.addActionListener(new ButtonListener());

		// making a gridbag panel to nest 2 grid panels (labels and textfields) for
		// CENTER
		JPanel flightFieldPanel = new JPanel();
		GridBagLayout gblFlightFieldBagLayout = new GridBagLayout();
		GridBagConstraints gbc2 = new GridBagConstraints();
		flightFieldPanel.setLayout(gblFlightFieldBagLayout);

		// making the 2 grid panels
		JPanel finderLabelsJPanel = new JPanel();
		JPanel finderComboJPanel = new JPanel();
		finderLabelsJPanel.setLayout(new GridLayout(3, 1, 0, 15));
		finderComboJPanel.setLayout(new GridLayout(3, 1));

		// making labels
		JLabel fromFinderJLabel = new JLabel("From:", SwingConstants.RIGHT);
		JLabel toFinderJLabel = new JLabel("To:", SwingConstants.RIGHT);
		JLabel dateFinderJLabel = new JLabel("Date:", SwingConstants.RIGHT);
		finderLabelsJPanel.add(fromFinderJLabel);
		finderLabelsJPanel.add(toFinderJLabel);
		finderLabelsJPanel.add(dateFinderJLabel);

		JComboBox fromFinderJComboBox = new JComboBox(flightAirportList.toArray());
		JComboBox toFinderJComboBox = new JComboBox(flightAirportList.toArray());
		JComboBox dateFinderJComboBox = new JComboBox(flightDateList);
		fromFinderJComboBox.addItemListener(new itemListener());
		toFinderJComboBox.addItemListener(new itemListener());
		dateFinderJComboBox.addItemListener(new itemListener());
		fromFinderJComboBox.setPreferredSize(new Dimension(700, 30)); // setting thge size of dropboxes
		finderComboJPanel.add(fromFinderJComboBox);
		finderComboJPanel.add(toFinderJComboBox);
		finderComboJPanel.add(dateFinderJComboBox);

		// adding my 2 grid panels inside my gridbag panels with constraint
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gblFlightFieldBagLayout.setConstraints(finderLabelsJPanel, gbc2);
		flightFieldPanel.add(finderLabelsJPanel);

		gbc2.gridx = 1;
		gbc2.gridy = 0;
		gbc2.gridheight = 1;
		gbc2.gridwidth = 9;
		gblFlightFieldBagLayout.setConstraints(finderComboJPanel, gbc2);
		flightFieldPanel.add(finderComboJPanel);

		// adding flight finder panel components
		flightFinderPanel.add(finderTitleJLabel, BorderLayout.NORTH);
		flightFinderPanel.add(flightFieldPanel, BorderLayout.CENTER);
		flightFinderPanel.add(findButton, BorderLayout.SOUTH);

		/*
		 * Building the center components to a gridbaglayout panel with constraints
		 * -------------------------------------------------------------
		 */
		JPanel centerJPanel = new JPanel();
		GridBagLayout gblCenterPanel = new GridBagLayout();
		centerJPanel.setLayout(gblCenterPanel);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridheight = 4;
		gbc.gridwidth = 3;
		gblCenterPanel.setConstraints(scrollPane, gbc);
		centerJPanel.add(scrollPane);

		gbc.gridy = 1;
		gbc.gridx = 3;
		gbc.gridheight = 4;
		gbc.gridwidth = 2;
		gblCenterPanel.setConstraints(reservePanel, gbc);
		centerJPanel.add(reservePanel);
		centerJPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 0, 0)); // adding a top and left border margin to
																				// the "center" (flight list box and
																				// reserve panel)

		/*
		 * Adding Panel components all together to create the Main Center panel
		 * -----------------------------------------------------
		 */

		panel.add(centerJPanel, BorderLayout.CENTER);
		panel.add(flightFinderPanel, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Item listener for our ComboBoxed in Flight Finders
	 * 
	 * @author Lenard
	 *
	 */
	private class itemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * An action listener for our buttons (findButton, and reserveButton)
	 * 
	 * @author Lenard
	 *
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	/**
	 * Called when user selects an item in the JList.
	 * 
	 * @author Lenard
	 */
	private class MyListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			if (!e.getValueIsAdjusting()) {
				//getting the object from our JList, (Alot of casting was needed here)
				JList source = (JList) e.getSource();
				DefaultListModel model = (DefaultListModel) source.getModel();
				int index = source.getSelectedIndex();
				Flight flight = (Flight) model.getElementAt(index);
				
				//assigning variables to be reflected at out input fields
				flightCode = flight.getCode();
				airlineName = flight.getAirlineName();
				day = flight.getWeekday();
				time = flight.getTime();
				cost = flight.getCostPerSeat();

				//setting value to be shown in reserve Text fields
				flightJTextField.setText(flightCode);
				airlineJTextField.setText(airlineName);
				dayJTextField.setText(day);
				timeJTextField.setText(time);
				costJTextField.setText(String.format("%,.2f", cost));
				
			}
		}

	}

}