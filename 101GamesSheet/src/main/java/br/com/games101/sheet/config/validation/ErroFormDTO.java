package br.com.games101.sheet.config.validation;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErroFormDTO {
	
	//subida
	@JsonView(ViewErro.Campo.class)
	private String campo;
	
	@JsonView(ViewErro.Erro.class)
	private String erro;
	
	@JsonView(ViewErro.Erro.class)
	private String dataErro;
}
