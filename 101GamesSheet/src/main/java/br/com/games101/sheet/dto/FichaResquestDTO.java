package br.com.games101.sheet.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.games101.sheet.entity.Ficha;
import lombok.Data;


@Data
public class FichaResquestDTO implements Serializable {

	private static final long serialVersionUID = 4815755529445540690L;

	@NotBlank @Length(min = 3, max = 45, message = "Tamanho do nome deve ser entre 3 e 45 caracteres.")
	private String nome;
	
	public Ficha convertEntity() {
		Ficha ficha = new Ficha();
		ficha.setNome(this.getNome());
		return ficha;
	}
	
}
