package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.exception.*;
import sait.frms.problemdomain.*;

public class ReservationManager {

	private ArrayList<Reservation> reservations = new ArrayList<>();

	private RandomAccessFile raf;
	
	private static final String BINARY_FILE = "res/reservations.bin";
	private static final String MODE = "rw";
	private static final int RESERVATION_SIZE = 121;
	
	public ReservationManager() throws IOException {
		
			this.raf = new RandomAccessFile(BINARY_FILE, MODE);	
		
	}
	
	// save all reservation objects to RAF
	public void persist() throws IOException {
		
		for (int i = 0; i < reservations.size(); i++) {
			// reservation code
			String code = String.format("%-5s", reservations.get(i).getCode()); 
			this.raf.writeUTF(code); //5+2 bytes
			// flight code
			String flightCode = String.format("%-7s", reservations.get(i).getFlightCode());
			this.raf.writeUTF(flightCode); //7+2 bytes
			// airline
			String airline = String.format("%-30s", reservations.get(i).getAirline());
			this.raf.writeUTF(airline); //30+2 bytes
			// name
			String name = String.format("%-30s", reservations.get(i).getName());
			this.raf.writeUTF(name); //30+2 bytes
			// citizenship
			String citizenship = String.format("%-30s", reservations.get(i).getCitizenship());
			this.raf.writeUTF(citizenship); //30+2 bytes
			//cost
			this.raf.writeDouble(reservations.get(i).getCost()); //8 bytes
			// is active?
			this.raf.writeBoolean(reservations.get(i).isActive()); //1 bytes
			
			
		}
	}
	
	private void populateFromBinary() throws IOException{
		
		for(long pos = 0; pos < this.raf.length(); pos += RESERVATION_SIZE) {
			this.raf.seek(pos);
			Reservation reservation = this.readRecord();
			reservations.add(reservation);
		}
	}
	
	// reads each reservation object from binary file
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
	
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws IOException, InvalidCitizenshipException, InvalidNameException, InvalidFlightException {
		
		// reservation object fields variables
		String code = "";
		String flightCode = "";
		String airline = "";
		String travelerName = "";
		String travelerCitizenship ="";
		double cost = 0;
		boolean active = false;
		
		// checks for exception in data
		if(flight.getSeats() > 0 || flight != null) {
			code = generateReservationCode(flight);
			flightCode = flight.getCode();
			airline = flight.getAirlineName();
			if(name != null) {
				travelerName = name;
				if(citizenship != null) {
					travelerCitizenship = citizenship;
					cost = flight.getCostPerSeat();
					active = true;
				} 
				 else {
					throw new InvalidCitizenshipException();
				} 
					
			} 
			 else {
				throw new InvalidNameException();
			} 
		} 
		/*else {
			throw new InvalidFlightException();
		} */ 
		// ^ needs try/catch somewhere
		
		Reservation createReservation = new Reservation(code, flightCode, airline, travelerName, travelerCitizenship, cost, active);
		
		// save reservation to binary file
		
		long position;
		
		for (position = 0; position < this.raf.length(); position += RESERVATION_SIZE) {
			this.raf.seek(position);
			
		}
		this.raf.seek(position);
		// reservation code
		String fileCode = String.format("%-5s", createReservation.getCode()); 
		this.raf.writeUTF(fileCode); //5+2 bytes
		// flight code
		String fileFlightCode = String.format("%-7s", createReservation.getFlightCode());
		this.raf.writeUTF(fileFlightCode); //7+2 bytes
		// airline
		String fileAirline = String.format("%-30s", createReservation.getAirline());
		this.raf.writeUTF(fileAirline); //30+2 bytes
		// name
		String fileName = String.format("%-30s", createReservation.getName());
		this.raf.writeUTF(fileName); //30+2 bytes
		// citizenship
		String fileCitizenship = String.format("%-30s", createReservation.getCitizenship());
		this.raf.writeUTF(fileCitizenship); //30+2 bytes
		//cost
		this.raf.writeDouble(createReservation.getCost()); //8 bytes
		// is active?
		this.raf.writeBoolean(createReservation.isActive()); //1 bytes
		
		flight.setSeats(flight.getSeats() -1);
		reservations.add(createReservation);
		
		return createReservation;
	}
	
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		ArrayList<Reservation> foundReservations = new ArrayList<>();
		
		try {
			this.raf.seek(0);
			for(long pos = 0; pos < this.raf.length(); pos += RESERVATION_SIZE) {
				Reservation reservation = this.readRecord();
				if(reservation.getCode().equalsIgnoreCase(code) || reservation.getAirline().equalsIgnoreCase(airline) || reservation.getName().equalsIgnoreCase(name)) {
					foundReservations.add(reservation);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return foundReservations;
	}
	
	
	public Reservation findReservationByCode(String code) {
		
		try {
			this.raf.seek(0);
			for(long pos = 0; pos < this.raf.length(); pos += RESERVATION_SIZE) {
				Reservation reservation = this.readRecord();
				if(reservation.getCode().equalsIgnoreCase(code)) {
					return reservation;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String generateReservationCode(Flight flight) {

		String resCode = "";
		int minCode = 1000;
		int maxCode = 9999;
		int numCode = 0;
		boolean domestic = flight.isDomestic();
		
		// domestic flights
		if(domestic == true) {
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
	
	private int getAvailableSeats(Flight flight) {
		int numSeats = flight.getSeats();
		return numSeats;
	}

	/* testing
	 * public static void main(String[] args) {
		String resCode = "";
		int minCode = 1000;
		int maxCode = 9999;
		int numCode = 0;
		resCode = "D";
		numCode = (int) ((Math.random() * (maxCode - minCode)) + minCode);
		resCode += numCode;
		System.out.println(resCode);
	} */
}
