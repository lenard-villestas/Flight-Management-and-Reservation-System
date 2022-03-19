/**
 * Class for Flight model
 * @author Lenard
 */
package sait.frms.problemdomain;

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
	 * Default Constructor
	 */
	public Flight() {
		super();
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
	public Flight(String code, String airlineName, String from, String to, String weekday, String time, int seats,
			double costPerSeat, boolean domestic) {
		super();
		this.code = code;
		this.airlineName = airlineName;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
		this.domestic = domestic;
	}


	/**
	 * Parse code ??
	 * @param code
	 */
	private void parseCode(String code) {
		setCode(code);
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
	 * @return the isDomestic boolean
	 */
	public boolean isDomestic() {
		
		return domestic;
	}

	
	@Override
	public String toString() {
		return "Flight [code=" + code + ", airlineName=" + airlineName + ", from=" + from + ", to=" + to + ", weekday="
				+ weekday + ", time=" + time + ", seats=" + seats + ", costPerSeat=" + costPerSeat + ", domestic ="+ domestic +"]";
	}
	
	
	
	
	
	
	
	
	
	
}
