package br.com.vibbra.organizerecipes.base;

import br.com.vibbra.organizerecipes.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Optional;

public abstract class BaseService {
	
	protected void validateRequiredFields(Map<String, Optional<?>> map) {
		Object[] camposNaoPreenchidos = map
			.entrySet()
			.stream()
			.filter(e -> e.getValue().isEmpty())
			.map(Map.Entry::getKey)
			.toArray();
		
		if (camposNaoPreenchidos.length > 0) {
			throw new ValidationException("Required fields must be completed: [" + StringUtils.join(camposNaoPreenchidos, " / ") + "]");
		}
	}
	
}
