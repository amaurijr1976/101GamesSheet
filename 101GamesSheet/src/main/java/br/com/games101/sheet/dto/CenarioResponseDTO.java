package br.com.games101.sheet.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.games101.sheet.entity.Cenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CenarioResponseDTO {
	
	private long id;
	private String nome;
	
	
	public static List<CenarioResponseDTO> convertDTO(List<Cenario> cenarios) {
        return  cenarios.stream()
        			  .map(cenario -> CenarioResponseDTO.convertDTO(Optional.of(cenario)))
        			  .collect(Collectors.toList());
	}
	
	public static CenarioResponseDTO convertDTO(Optional<Cenario> cenario) {
        if(cenario.isEmpty()) {
        	return null;
        }else{
        	return  CenarioResponseDTO.builder()
				.id(cenario.get().getId())
				.nome(cenario.get().getNome())
				.build();
        }
	}
}
