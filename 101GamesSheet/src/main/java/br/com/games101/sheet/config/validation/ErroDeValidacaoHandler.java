package br.com.games101.sheet.config.validation;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormDTO> handle(MethodArgumentNotValidException exception) {
		List<FieldError> erros = exception.getBindingResult().getFieldErrors();
		return erros.stream().map(e -> {String mensagemErro = messageSource.getMessage(e, LocaleContextHolder.getLocale());
										return ErroFormDTO.builder().campo(e.getField()).erro(mensagemErro).build();
										}).collect(Collectors.toList());
	}
	
}
