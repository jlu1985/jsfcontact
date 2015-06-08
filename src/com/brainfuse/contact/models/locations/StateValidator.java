package com.brainfuse.contact.models.locations;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesValidator("stateValidator")
public class StateValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(StateValidator.class);
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		logger.debug("Context {}, Component {}, Object {}", context, component, value);
	}

}
