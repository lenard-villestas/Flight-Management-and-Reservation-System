package sait.frms.exception;

/**
 * This exception is thrown when a valid airline cannot be parse from the flight
 * code.
 * 
 * @author Patrick
 * @version 1
 */
public class InvalidAirLineException extends Exception {

	public InvalidAirLineException() {
		super("The airline selected is incorrect.");
	}

}
