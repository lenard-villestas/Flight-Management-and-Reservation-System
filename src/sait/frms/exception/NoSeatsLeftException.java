package sait.frms.exception;

/**
 * This exception is thrown when a user tries to make a reservation for a flight
 * with 0 seats available.
 * 
 * @author Lenard
 * @version 1
 */
public class NoSeatsLeftException extends Exception {

	public NoSeatsLeftException() {

	}

}
