package br.com.roma.infraestructure.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidationConfig {
	
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
				bean.setValidationMessageSource(messageSource);
				return bean;
		
			}

}
