package br.com.vibbra.organizerecipes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String error;

	@Override
	public String toString(){
	    return "AppException["+error+"]";
	}  
}
