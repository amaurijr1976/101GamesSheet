package br.com.games101.sheet.dto.relacaoCenario;

import java.util.List;

import br.com.games101.sheet.dto.FeiticoResponseDTO;
import br.com.games101.sheet.entity.Cenario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeiticosPorCenarioResponseDTO {

	private String nomeCenario ;
	
	private List<FeiticoResponseDTO> listaItens;

	static public FeiticosPorCenarioResponseDTO convertDTO(Cenario cenario){
		return FeiticosPorCenarioResponseDTO.builder()
				.nomeCenario(cenario.getNome())
				.listaItens(FeiticoResponseDTO.convertDTO(cenario.getFeiticos()))
				.build();
	}
}
