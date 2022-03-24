package sait.frms.exception;

/**
 * Thrown when citizenship is empty or null
 * 
 * @author Javaria
 * @version 1
 */
public class InvalidCitizenshipException extends Exception {

	public InvalidCitizenshipException() {
		super("Citizenship is empty and invalid.");
	}
}
