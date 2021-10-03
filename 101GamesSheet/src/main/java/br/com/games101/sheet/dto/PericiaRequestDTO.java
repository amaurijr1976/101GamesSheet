package br.com.games101.sheet.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.games101.sheet.validation.CenarioExistAnnotation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PericiaRequestDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@NotBlank @Length(min = 3, max = 45, message = "Tamanho do nome deve ser entre 3 e 45 caracteres.")
	private String nome;
	
	@NotBlank @Length(min = 3, max = 45, message = "Tamanho do tipo deve ser entre 3 e 45 caracteres.")
	private String tipo;
	
	@NotBlank @Length(min = 3, max = 400, message = "Tamanho do descricao deve ser entre 3 e 400 caracteres.")
	private String descricao;
		
	@Positive(message = "Conveio deve ser um numero positivo")
	@CenarioExistAnnotation
	private Long cenario;
}
