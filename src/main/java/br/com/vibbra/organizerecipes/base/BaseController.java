package br.com.vibbra.organizerecipes.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
	
	@Autowired
	protected HttpServletRequest request;
}
