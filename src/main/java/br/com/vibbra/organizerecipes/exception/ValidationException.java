package br.com.vibbra.organizerecipes.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationException extends AppException {
	
	private static final long serialVersionUID = 1L;
	
	public ValidationException(String error){
		super(error);
	}

	@Override
	public String toString(){
	    return "ValidationException["+getError()+"]";
	}  
}
