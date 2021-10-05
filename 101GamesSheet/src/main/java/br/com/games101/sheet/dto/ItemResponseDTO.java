package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.games101.sheet.entity.Item;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private String tipo;
	private String descricao;
	private Long cenario;

	static public List<ItemResponseDTO> convertDTO(List<Item> listaItens){
		return listaItens.stream().map(item -> ItemResponseDTO.convertDTO(item)).collect(Collectors.toList());
	}

	public static ItemResponseDTO convertDTO(Item item) {
		return ItemResponseDTO.builder()
							  .id(item.getId())
							  .nome(item.getNome())
							  .tipo(item.getTipo())
							  .descricao(item.getDescricao())
							  .cenario(item.getCenario().getId())
							  .build();
	}
}
