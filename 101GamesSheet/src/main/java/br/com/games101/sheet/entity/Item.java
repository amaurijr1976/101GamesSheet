package br.com.games101.sheet.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.CenarioResquestDTO;
import br.com.games101.sheet.dto.ItemRequestDTO;
import br.com.games101.sheet.dto.ItemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tb_item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String tipo;
	
	private String descricao;
	
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="ficha", nullable=false)
   private Cenario cenario;
	
	static public Item retornaEntity(ItemRequestDTO request,CenarioResponseDTO cenario) {
		Item item = null;
		if(request.getCenario() == cenario.getId()) {
			item = Item.builder()
						 .nome(request.getNome())
						 .descricao(request.getDescricao())
						 .tipo(request.getTipo())
						 .cenario(Cenario.retornaEntity(cenario))
						 .build();
		}
		return item;
	}

	public static Item retornaEntityAtualizacao(@Valid ItemRequestDTO itemRequest,CenarioResponseDTO cenarioResponseDTO,Long id) {
		Item item = retornaEntity(itemRequest, cenarioResponseDTO);
		item.setId(id);
		return item;
	}
   
}
