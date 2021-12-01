package br.com.games101.sheet.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_item_personagem")
@Table(name = "tb_item_personagem")
public class PersonagemItems implements Serializable{

	
	private static final long serialVersionUID = 3751079218160958868L;

	@EmbeddedId
	private PersonagemItemsId personagemItemId;
	
    @ManyToOne()
    @MapsId("idItem")
    @JoinColumn(name = "item_id")
    private Item item;
 
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne()
    @MapsId("idPersonagem")
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;
	
	private long quantidade;
}
