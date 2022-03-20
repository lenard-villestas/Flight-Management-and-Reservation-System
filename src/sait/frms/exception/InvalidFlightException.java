package sait.frms.exception;

public class InvalidFlightException extends Exception{
	
	public InvalidFlightException() {
		super("Flight is booked, or does not exist");
	}
	
}
