package sait.frms.exception;

public class InvalidNameException extends Exception {

	public InvalidNameException() {
		super("Name is empty and invalid.");
	}
}
