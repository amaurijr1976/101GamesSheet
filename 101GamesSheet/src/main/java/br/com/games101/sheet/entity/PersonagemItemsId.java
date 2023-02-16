package br.com.games101.sheet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PersonagemItemsId implements Serializable {


	private static final long serialVersionUID = 2930175605880880584L;

	@Column(name="id_personagem")
	private long idPersonagem;
	
	@Column(name="id_item")
	private long idItem;
	
}
