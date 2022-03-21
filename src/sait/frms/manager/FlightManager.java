package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.problemdomain.*;

public class FlightManager {

	private final String APPath = "res/airports.csv";
	private final String FLPath = "res/flights.csv";

	private ArrayList<String> airports = new ArrayList<>();
	private ArrayList<Flight> flights = new ArrayList<>();

	public FlightManager() {

		try {
			populateFlights();
			populateAirports();
		} catch (IOException e) {
			System.out.println("Required Files not Found, please contact IT");
		}

	}

	/*
	 * -Lenard This is just for testing
	 */
	public void test() {
		try {
			populateFlights();
			populateAirports();
		} catch (IOException e) {

		}

		// Testing flights array

		for (int i = 0; i < flights.size(); i++) {

			System.out.println(flights.get(i).toString());
		}

		// Testing aiports array
		for (int i = 0; i < airports.size(); i++) {

			System.out.println(airports.get(i).toString());
		}

	}

	private void populateFlights() throws IOException {
		File input = new File(FLPath);
		Scanner in = new Scanner(input);

		while (in.hasNext()) {
			String line = in.nextLine();
			String[] fields = line.split(",");
			flights.add(new Flight(fields[0], fields[1], fields[2], fields[3], fields[4], Integer.parseInt(fields[5]),
					Double.parseDouble(fields[6])));

		}

		in.close();
	}

	private void populateAirports() throws IOException {
		File input = new File(APPath);
		Scanner in = new Scanner(input);

		while (in.hasNext()) {
			String line = in.nextLine();
			String[] fields = line.split(",");
			airports.add(fields[0]);
		}

		in.close();
	}

	public ArrayList<String> getAirports() {
		return this.airports;
	}

	public ArrayList<Flight> getFlights() {
		return this.flights;
	}

	/**
	 * This method is to return flights that will be displayed in the control list
	 * of GUI
	 * 
	 * @param from
	 * @param to
	 * @param weekday
	 * @return foundFlights arraylist of Flights
	 * @author Lenard
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> foundFlights = new ArrayList<>();
		String fromAirport = from;
		String toAirport = to;
		String day = weekday;

		for (int i = 0; i < flights.size(); i++) {
			String flightFrom = flights.get(i).getFrom();
			String flightTo = flights.get(i).getTo();
			String flightDay = flights.get(i).getWeekday();
			
			
			switch (day) {
			
			case "ANY":
				
				if (fromAirport.equals(flightFrom) && toAirport.equals(flightTo)) {
					foundFlights.add(flights.get(i));
				}
				break;

			default:
				if (fromAirport.equals(flightFrom) && toAirport.equals(flightTo) && day.equals(flightDay)) {
					foundFlights.add(flights.get(i));
				}
			}
		}
		
		//System.out.println("flights array: " + flights.size() + " from: " + from + ", to: " + to + ", week: " + day + " foundflights: " + foundFlights.size());
		
		return foundFlights;
	}

}
