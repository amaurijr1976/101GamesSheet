package br.com.games101.sheet.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.games101.sheet.entity.PersonagemItems;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ItemPersonagemResponseDTO {

    private ItemResponseDTO item;
	
	private long quantidade;


	static public List<ItemPersonagemResponseDTO> convertDTO(Set<PersonagemItems> listaItens){
		return listaItens.stream().map(item -> ItemPersonagemResponseDTO.convertDTO(item)).collect(Collectors.toList());
	}

	static public List<ItemPersonagemResponseDTO> convertDTO(List<PersonagemItems> listaItens){
		return listaItens.stream().map(item -> ItemPersonagemResponseDTO.convertDTO(item)).collect(Collectors.toList());
	}
	
    static public ItemPersonagemResponseDTO convertDTO(PersonagemItems item){
    	return ItemPersonagemResponseDTO.builder()
    								 .item(ItemResponseDTO.convertDTO(item.getItem()))
    								 .quantidade(item.getQuantidade())
    								 .build();
    }
	
}
