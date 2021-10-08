package br.com.games101.sheet.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity(name = "tb_id_personagem")
public class PersonagemItems{

	
	@EmbeddedId
	private PersonagemItemsId personagemItemId;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("itemId")
    private Item item;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("personagemId")
    private Personagem personagem;
	
	private long quantidade;
	
	private long dinheiro;
}
