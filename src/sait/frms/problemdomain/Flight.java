/**
 * Class for Flight model
 * @author Lenard
 */
package sait.frms.problemdomain;

import sait.frms.exception.*;

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

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param airlineName
	 * @param from
	 * @param to
	 * @param weekday
	 * @param time
	 * @param seats
	 * @param costPerSeat
	 * @param domestic
	 */
	public Flight(String code, String from, String to, String weekday, String time, int seats, double costPerSeat) {

		this.code = code;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;

		parseCode(code);
		
	}

	private void parseCode(String code) {
		String airLine = "bruh";
		String abbr = code.substring(0, 2);
		
		try {
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
				throw new InvalidAirLineException("Invalid AirLine Detected, Please check if Flight Code is valid.");

			}
		} catch (InvalidAirLineException e) {

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

	/**
	 * @param domestic the domestic to set
	 */
	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}

	@Override
	public String toString() {
		return "Flight [code=" + code + ", airlineName=" + airlineName + ", from=" + from + ", to=" + to + ", weekday="
				+ weekday + ", time=" + time + ", seats=" + seats + ", costPerSeat=" + costPerSeat + "]";
	}

}
