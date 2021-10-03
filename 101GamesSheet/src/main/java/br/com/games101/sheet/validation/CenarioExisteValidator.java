package br.com.games101.sheet.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.repository.CenarioRepository;

public class CenarioExisteValidator implements ConstraintValidator<CenarioExistAnnotation,Long> {

	@Autowired
	private CenarioRepository cenarioRepository;

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		 Optional<Cenario> cenario = cenarioRepository.findById(value);
		return (cenario.isPresent())?true:false;
	}
	


}
