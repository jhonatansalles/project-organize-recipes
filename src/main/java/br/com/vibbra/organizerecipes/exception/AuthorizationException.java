package br.com.vibbra.organizerecipes.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthorizationException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String error){
		super(error);
	}

	@Override
	public String toString(){
	    return "ValidationException["+getError()+"]";
	}  
}
