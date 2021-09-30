package br.com.games101.sheet.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.games101.sheet.entity.Ficha;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FichaResponseDTO {
	
	private long id;
	private String nome;
	
	
	public static List<FichaResponseDTO> convertDTO(List<Ficha> fichas) {
        return  fichas.stream()
        			  .map(ficha -> FichaResponseDTO.convertDTO(ficha))
        			  .collect(Collectors.toList());
	}
	
	public static FichaResponseDTO convertDTO(Ficha ficha) {
        return  FichaResponseDTO.builder()
				.id(ficha.getId())
				.nome(ficha.getNome())
				.build();
	}
}
