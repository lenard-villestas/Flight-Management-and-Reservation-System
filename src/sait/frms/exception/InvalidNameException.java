package sait.frms.exception;

/**
 * Thrown when Name field is empty or null
 * 
 * @author Javaria
 * @version 1
 */
public class InvalidNameException extends Exception {

	public InvalidNameException() {
		super("Name is empty and invalid.");
	}
}
