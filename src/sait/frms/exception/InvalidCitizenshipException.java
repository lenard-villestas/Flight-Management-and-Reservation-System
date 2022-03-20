package sait.frms.exception;

public class InvalidCitizenshipException extends Exception{

	public InvalidCitizenshipException() {
		super("Citizenship is empty and invalid.");
	}
}
