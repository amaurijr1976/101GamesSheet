package br.com.games101.sheet.dto;

import java.io.Serializable;
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
public class CenarioResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	
	
	public static List<CenarioResponseDTO> convertDTO(List<Cenario> cenarios) {
        return  cenarios.stream()
        			  .map(cenario -> CenarioResponseDTO.convertDTO(cenario))
        			  .collect(Collectors.toList());
	}
	
	public static CenarioResponseDTO convertDTO(Cenario cenario) {
        	return  CenarioResponseDTO.builder()
				.id(cenario.getId())
				.nome(cenario.getNome())
				.build();
	}
}
