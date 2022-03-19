package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.problemdomain.*;

public class FlightManager {

	private final String APPath = "res/airports.csv";
	private final String FLPath = "res/flights.csv";

	private static ArrayList<String> airports = new ArrayList<>();
	private static ArrayList<Flight> flights = new ArrayList<>();

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

}
