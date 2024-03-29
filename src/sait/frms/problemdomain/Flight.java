
package sait.frms.problemdomain;

import sait.frms.exception.*;

/**
 * Models the Flight Object
 * 
 * @author Patrick,Lenard,Javaria
 * @version March 23, 2022
 */

public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	private boolean domestic;
	
	/**
	 * Default constructor for Flight 
	 */
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a flight with (String code, String from, String to, String weekday, String time, int seats, double costPerSeat)
	 * @param code
	 * @param airlineName
	 * @param from
	 * @param to
	 * @param weekday
	 * @param time
	 * @param seats
	 * @param costPerSeat
	 * @param domestic
	 * @throws InvalidAirLineException
	 */
	public Flight(String code, String from, String to, String weekday, String time, int seats, double costPerSeat)
			throws InvalidAirLineException {

		this.code = code;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;

		parseCode(code);

	}
	/**
	 * Will give the airline name of a flight using it's flight code
	 * @param code
	 * @throws InvalidAirLineException
	 */
	private void parseCode(String code) throws InvalidAirLineException {
		String airLine = "bruh";
		String abbr = code.substring(0, 2);

		switch (abbr) {
		case "OA":
			airLine = "Otto Airlines";
			this.airlineName = airLine;
			break;
		case "VA":
			airLine = "Vertical Airways";
			this.airlineName = airLine;
			break;
		case "TB":
			airLine = "Try a Bus Airways";
			this.airlineName = airLine;
			break;
		case "CA":
			airLine = "Conned Air";
			this.airlineName = airLine;
			break;
		default:
			throw new InvalidAirLineException();
		}

	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the airlineName
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @return the weekday
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * @return the costPerSeat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}

	/**
	 * @return the domestic
	 */
	public boolean isDomestic() {
		char fromAirportLetter = this.from.charAt(0);
		char toAirportLetter = this.to.charAt(0);

		if (fromAirportLetter == 'Y' && toAirportLetter == 'Y') {
			this.domestic = true;
		} else {
			this.domestic = false;
		}

		return domestic;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param airlineName the airlineName to set
	 */
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @param weekday the weekday to set
	 */
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @param seats the seats to set
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * @param costPerSeat the costPerSeat to set
	 */
	public void setCostPerSeat(double costPerSeat) {
		this.costPerSeat = costPerSeat;
	}

	@Override
	public String toString() {
		return code + ", From: " + from + ", To:" + to + ", Day:" + weekday + ", Cost:"
				+ String.format("%,.2f", costPerSeat);
	}

}
