package br.com.games101.sheet.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import br.com.games101.sheet.validation.CenarioExistAnnotation;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MelhoriaRefugioRequestDTO implements Serializable {

	private static final long serialVersionUID = -6312747277822146165L;
	
	@NotBlank @Length(min = 3, max = 45, message = "Tamanho do nome deve ser entre 3 e 45 caracteres.")
	private String nome;
	
	@NotBlank @Length(min = 3, max =1000, message = "Tamanho do descricao deve ser entre 3 e 1000 caracteres.")
	private String descricao;
	
	@PositiveOrZero
	private long custo_tecnologia;
	
	@PositiveOrZero
	private long custo_espaco;
	
	@PositiveOrZero
	private long bonus_defesa;
	
	@Positive(message = "Convenio deve ser um numero positivo")
	@CenarioExistAnnotation
	private long cenario;

}
