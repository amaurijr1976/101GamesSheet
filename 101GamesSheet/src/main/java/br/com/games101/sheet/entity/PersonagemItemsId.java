package br.com.games101.sheet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PersonagemItemsId implements Serializable {


	private static final long serialVersionUID = 2930175605880880584L;

	@Column(name="id_personagem")
	private long personagemId;
	
	@Column(name="id_item")
	private long itemId;
	
}
