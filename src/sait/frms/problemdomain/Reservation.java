package sait.frms.problemdomain;

/**
 * Models the Reservation Object
 * @author Patrick,Lenard,Javaria
 * @version March 23, 2022
 */

public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
	
	/**
	 * default Constructor for Reservation Model
	 */
	public Reservation() {
		super();
		
	}
	
	
	/**
	 * Constructs a Reservation with <p>(String code, String flightCode, String airline, String name, String citizenship, double cost, boolean active)
	 * @param code
	 * @param flightCode
	 * @param name
	 * @param citizenship
	 * @param cost
	 * @param active
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost, boolean active) {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	
	/**
	 * 
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param flightCode the flightCode to set
	 */
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	/**
	 * 
	 * @param airline the airline to set
	 */
	
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return code;
	}
	
	/**
	 * Used to check by reservation finder for a reservation match in the arraylist
	 * @param obj
	 * @return equal a boolean
	 */
	public boolean equals(Reservation obj) {
		boolean equal = false;
		
		if (getCode().equals(obj.getCode()) && getFlightCode().equals(obj.getFlightCode())) {
			equal = true;
		}
		
		
		return equal;
	}
}
