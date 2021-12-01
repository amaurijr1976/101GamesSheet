package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.entity.Cenario;
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
	private Cenario cenario;

	static public List<ItemResponseDTO> convertDTO(List<Item> listaItens){
		return listaItens.stream().map(item -> ItemResponseDTO.convertDTO(item)).collect(Collectors.toList());
	}

    static public ItemResponseDTO convertDTO(Item item){
    	return ItemResponseDTO.builder()
    						  .cenario(item.getCenario())
    						  .descricao(item.getDescricao())
    						  .nome(item.getNome())
    						  .tipo(item.getTipo())
    						  .id(item.getId())
    						  .build();
    }
}
