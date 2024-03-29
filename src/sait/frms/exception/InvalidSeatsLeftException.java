package sait.frms.exception;

/**
 * This exception is thrown when a user tries to make a reservation for a flight
 * with 0 seats available.
 * 
 * @author Patrick
 * @version 1
 */
public class InvalidSeatsLeftException extends Exception {

	public InvalidSeatsLeftException() {
		super("The numbers of seats is invalid.");
	}

}
