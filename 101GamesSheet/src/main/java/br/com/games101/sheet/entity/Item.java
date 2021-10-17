package br.com.games101.sheet.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.games101.sheet.dto.ItemRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tb_item")
public class Item implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String tipo;
	
	private String descricao;
	
	@OneToMany(fetch = FetchType.EAGER,
			mappedBy = "item",
			cascade=CascadeType.ALL,
			orphanRemoval = true)
	private List<PersonagemItems> personagemItems;
	
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="cenario", nullable=false)
   private Cenario cenario;
	
	static public Item retornaEntity(ItemRequestDTO request,Optional<Cenario> cenario) {
			return Item.builder()
						 .nome(request.getNome())
						 .descricao(request.getDescricao())
						 .tipo(request.getTipo())
						 .cenario(cenario.get())
						 .build();
	}
}
