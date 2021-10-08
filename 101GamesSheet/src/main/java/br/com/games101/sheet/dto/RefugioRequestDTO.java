package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import br.com.games101.sheet.entity.MelhoriaRefugio;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefugioRequestDTO implements Serializable {

	private static final long serialVersionUID = -2153546629899998852L;
	
	@NotBlank
	@Length(min = 3, max = 45, message = "Tamanho do nome deve ser entre 3 e 45 caracteres.")
	private String nome;
	
	@NotBlank
	@Length(min = 3, max = 45, message = "Tamanho do nome deve ser entre 3 e 45 caracteres.")
	private String local;
	
	@PositiveOrZero
	private long defesa;
	
	@PositiveOrZero
	private long tecnologia;
	
	@PositiveOrZero
	private long espaco;

	private List<MelhoriaRefugio> melhoriasRefugio;

}
