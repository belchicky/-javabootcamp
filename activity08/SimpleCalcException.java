package jtm.activity08;

public class SimpleCalcException extends Exception {

	private static final long serialVersionUID = -7924068698262071643L;

	public SimpleCalcException(String message, Throwable cause) {
		super(message, cause);

	}

	public SimpleCalcException(String message) {
		super(message);
	}

}