package sait.frms.exception;

/**
 * Thrown if flight doesnt exist or invalid.
 * 
 * @author Javaria
 * @version 1
 */
public class InvalidFlightException extends Exception {

	public InvalidFlightException() {
		super("Flight is booked, or does not exist");
	}

}
