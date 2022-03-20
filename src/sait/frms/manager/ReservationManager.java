package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.problemdomain.Reservation;

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
}
