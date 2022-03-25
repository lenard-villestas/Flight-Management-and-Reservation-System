package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.exception.*;
import sait.frms.problemdomain.*;

/**
 * Handles all reservation related methods
 * <p>
 * finding, making, updating, reading and saving reservations to a RAF
 * 
 * @author Lenard, Javaria, Patrick
 * @version March 23, 2022
 */
public class ReservationManager {

	private ArrayList<Reservation> reservations = new ArrayList<>();

	private RandomAccessFile raf;

	private static final String BINARY_FILE = "res/reservations.bin";
	private static final String MODE = "rw";
	private static final int RESERVATION_SIZE = 121;

	/**
	 * Default constructor for ReservationManager
	 * <p>
	 * will make an RAF and populate it with the reservations array on call
	 */
	public ReservationManager() {

		try {
			this.raf = new RandomAccessFile(BINARY_FILE, MODE);
			populateFromBinary();
		} catch (IOException ioe) {
			System.out.println("Binary file cant be loaded");
		}

	}

	/**
	 * Persist will save all reservation objects from the arraylist to RAF
	 * 
	 * @author Javaria
	 * @version March 23, 2022
	 * @throws IOException
	 */
	public void persist() throws IOException {

		long pos = 0;
		for (int i = 0; i < reservations.size(); i++) {

			this.raf.seek(pos);
			// reservation code
			String code = String.format("%-5s", reservations.get(i).getCode());
			this.raf.writeUTF(code); // 5+2 bytes
			// flight code
			String flightCode = String.format("%-7s", reservations.get(i).getFlightCode());
			this.raf.writeUTF(flightCode); // 7+2 bytes
			// airline
			String airline = String.format("%-30s", reservations.get(i).getAirline());
			this.raf.writeUTF(airline); // 30+2 bytes
			// name
			String name = String.format("%-30s", reservations.get(i).getName());
			this.raf.writeUTF(name); // 30+2 bytes
			// citizenship
			String citizenship = String.format("%-30s", reservations.get(i).getCitizenship());
			this.raf.writeUTF(citizenship); // 30+2 bytes
			// cost
			this.raf.writeDouble(reservations.get(i).getCost()); // 8 bytes
			// is active?
			this.raf.writeBoolean(reservations.get(i).isActive()); // 1 bytes
			pos += RESERVATION_SIZE;

		}
	}

	/**
	 * loads RAF to reservations array list
	 * 
	 * @author Javaria
	 * @version March 23, 2022
	 * @throws IOException
	 */
	private void populateFromBinary() throws IOException {

		for (long pos = 0; pos < this.raf.length(); pos += RESERVATION_SIZE) {
			this.raf.seek(pos);
			Reservation reservation = this.readRecord();
			reservations.add(reservation);
		}
	}

	/**
	 * Reads each reservation object from binary file
	 * 
	 * @author Javaria
	 * @version March 23, 2022
	 * @return r a reservation
	 * @throws IOException
	 */
	private Reservation readRecord() throws IOException {

		String code = this.raf.readUTF().trim();
		String flightCode = this.raf.readUTF().trim();
		String airline = this.raf.readUTF().trim();
		String name = this.raf.readUTF().trim();
		String citizenship = this.raf.readUTF().trim();
		double cost = this.raf.readDouble();
		boolean active = this.raf.readBoolean();

		Reservation r = new Reservation(code, flightCode, airline, name, citizenship, cost, active);
		return r;

	}

	/**
	 * Will make a reservation and add that to the array and also persist the new
	 * array to RAF
	 * 
	 * @author Javaria
	 * @version March 23, 2022
	 * @param flight
	 * @param name
	 * @param citizenship
	 * @return a Reservation object
	 * @throws IOException
	 * @throws InvalidCitizenshipException
	 * @throws InvalidNameException
	 * @throws InvalidFlightException
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship)
			throws IOException, InvalidCitizenshipException, InvalidNameException, InvalidFlightException {

		// reservation object fields variables
		String code = "";
		String flightCode = "";
		String airline = "";
		String travelerName = "";
		String travelerCitizenship = "";
		double cost = 0;
		boolean active = false;

		// checks for exception in data
		if (flight.getSeats() > 0 || flight != null) {
			code = generateReservationCode(flight);
			flightCode = flight.getCode();
			airline = flight.getAirlineName();
			if (name != null) {
				travelerName = name;
				if (citizenship != null) {
					travelerCitizenship = citizenship;
					cost = flight.getCostPerSeat();
					active = true;
				} else {
					throw new InvalidCitizenshipException();
				}

			} else {
				throw new InvalidNameException();
			}
		}
		

		Reservation createReservation = new Reservation(code, flightCode, airline, travelerName, travelerCitizenship,
				cost, active);

		// save reservation to binary file

		long position;

		for (position = 0; position < this.raf.length(); position += RESERVATION_SIZE) {
			this.raf.seek(position);

		}
		this.raf.seek(position);
		// reservation code
		String fileCode = String.format("%-5s", createReservation.getCode());
		this.raf.writeUTF(fileCode); // 5+2 bytes
		// flight code
		String fileFlightCode = String.format("%-7s", createReservation.getFlightCode());
		this.raf.writeUTF(fileFlightCode); // 7+2 bytes
		// airline
		String fileAirline = String.format("%-30s", createReservation.getAirline());
		this.raf.writeUTF(fileAirline); // 30+2 bytes
		// name
		String fileName = String.format("%-30s", createReservation.getName());
		this.raf.writeUTF(fileName); // 30+2 bytes
		// citizenship
		String fileCitizenship = String.format("%-30s", createReservation.getCitizenship());
		this.raf.writeUTF(fileCitizenship); // 30+2 bytes
		// cost
		this.raf.writeDouble(createReservation.getCost()); // 8 bytes
		// is active?
		this.raf.writeBoolean(createReservation.isActive()); // 1 bytes

		flight.setSeats(flight.getSeats() - 1);
		reservations.add(createReservation);

		return createReservation;
	}

	/**
	 * Will search for matched reservations in the array
	 * 
	 * @author Lenard
	 * @version March 23, 2022
	 * @param code
	 * @param airline
	 * @param name
	 * @return an array of Reservations
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {

		ArrayList<Reservation> foundReservations = new ArrayList<>();
		String findCode = code;
		String findAirline = airline;
		String findName = name;
		// for testing System.out.println("find: " + code + airline + name);

		for (int i = 0; i < reservations.size(); i++) {
			String resCode = reservations.get(i).getCode();
			String resAirline = reservations.get(i).getAirline();
			String resName = reservations.get(i).getName();

			// find any
			if (findCode == null && findAirline == null && findName == null) {

				foundReservations.add(reservations.get(i));

				// find by name
			} else if (findAirline == null && findCode == null) {

				if (findName.equalsIgnoreCase(resName)) {
					foundReservations.add(reservations.get(i));
				}

				// find by code
			} else if (findAirline == null && findName == null) {

				if (findCode.equalsIgnoreCase(resCode)) {
					foundReservations.add(reservations.get(i));
				}

				// find by airline
			} else if (findCode == null && findName == null) {

				if (findAirline.equalsIgnoreCase(resAirline)) {
					foundReservations.add(reservations.get(i));
				}

				// find by airline and name
			} else if (findCode == null) {

				if (findAirline.equalsIgnoreCase(resAirline) && findName.equalsIgnoreCase(resName)) {
					foundReservations.add(reservations.get(i));
				}
				// find by airline and code
			} else if (findName == null) {

				if (findAirline.equalsIgnoreCase(resAirline) && findCode.equalsIgnoreCase(resCode)) {
					foundReservations.add(reservations.get(i));
				}

				// find by name and code
			} else if (findAirline == null) {

				if (findName.equalsIgnoreCase(resName) && findCode.equalsIgnoreCase(resCode)) {
					foundReservations.add(reservations.get(i));
				}
			}
		}

		return foundReservations;
	}

	/**
	 * Intends to find reservation in the array from an input code
	 * 
	 * @param code
	 * @return a reservation
	 */
	public Reservation findReservationByCode(String code) {

		try {
			this.raf.seek(0);
			for (long pos = 0; pos < this.raf.length(); pos += RESERVATION_SIZE) {
				Reservation reservation = this.readRecord();
				if (reservation.getCode().equalsIgnoreCase(code)) {
					return reservation;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generates a reservation code from flight code
	 * 
	 * @author Javaria
	 * @version March 23, 2022
	 * @param flight
	 * @return resCode String
	 */
	private String generateReservationCode(Flight flight) {

		String resCode = "";
		int minCode = 1000;
		int maxCode = 9999;
		int numCode = 0;
		boolean domestic = flight.isDomestic();

		// domestic flights
		if (domestic == true) {
			resCode = "D";
			numCode = (int) ((Math.random() * (maxCode - minCode)) + minCode);
			resCode += numCode;
		}
		// international flights
		else {
			resCode = "I";
			numCode = (int) ((Math.random() * (maxCode - minCode)) + minCode);
			resCode += numCode;
		}

		return resCode;
	}

	/**
	 * Will return available seats of a flight
	 * 
	 * @param flight
	 * @return numSeats number of seats
	 */
	private int getAvailableSeats(Flight flight) {
		int numSeats = flight.getSeats();
		return numSeats;
	}

	/**
	 * Will persist the change to a reservation on the array and RAF
	 * 
	 * @author Lenard
	 * @version March 23, 2022
	 * @param reservation
	 */
	public void updateReservation(Reservation reservation) {
		Reservation updatedReservation = reservation;

		try {
			for (int i = 0; i < reservations.size(); i++) {

				if (reservations.get(i).equals(updatedReservation)) {
					// System.out.println("Match");
					// reservations.set(i, updatedReservation);

					reservations.get(i).setName(updatedReservation.getName());
					reservations.get(i).setCitizenship(updatedReservation.getCitizenship());
					reservations.get(i).setActive(updatedReservation.isActive());
					System.out.println("Saved");
				}

			}

			persist();
		} catch (IOException e) {
			// TODO: handle exception
		} catch (RuntimeException rte) {

		}

	}

}
