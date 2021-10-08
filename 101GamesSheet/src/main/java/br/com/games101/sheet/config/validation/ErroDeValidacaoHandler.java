package br.com.games101.sheet.config.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.games101.sheet.constant.FormatoData;
import br.com.games101.sheet.util.Util;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@JsonView(ViewErro.Campo.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public List<ErroFormDTO> handle(MethodArgumentNotValidException exception) {
		List<FieldError> erros = exception.getBindingResult().getFieldErrors();
		return erros.stream().map(e -> {String mensagemErro = messageSource.getMessage(e, LocaleContextHolder.getLocale());
										return ErroFormDTO.builder()
											   .campo(e.getField())
											   .erro(mensagemErro)
											   .dataErro(new Util().retornaDataAtualFormatado(FormatoData.FORMATTERDATAERROSIMPLES.getLabel()))
											   .build();
										}).collect(Collectors.toList());
	}
	
	
	@JsonView(ViewErro.Erro.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public ErroFormDTO handle(HttpMessageNotReadableException exception) {
		return ErroFormDTO.builder()
				  .erro(exception.getMessage())
				   .dataErro(new Util().retornaDataAtualFormatado(FormatoData.FORMATTERDATAERROSIMPLES.getLabel()))
				   .build();
	}

	@JsonView(ViewErro.Erro.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({DataIntegrityViolationException.class,JpaObjectRetrievalFailureException.class})
	public ErroFormDTO handle(Exception exception) {
		return ErroFormDTO.builder()
						  .erro(exception.getMessage())
						   .dataErro(new Util().retornaDataAtualFormatado(FormatoData.FORMATTERDATAERROSIMPLES.getLabel()))
						   .build();
	}
	
	
	
}
