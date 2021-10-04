package br.com.games101.sheet.dto.relacaoCenario;

import java.util.List;

import br.com.games101.sheet.dto.PericiaResponseDTO;
import br.com.games101.sheet.entity.Cenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PericiasPorCenarioResponseDTO {

	private String nomeCenario ;
	
	private List<PericiaResponseDTO> listaItens;

	static public PericiasPorCenarioResponseDTO convertDTO(Cenario cenario){
		return PericiasPorCenarioResponseDTO.builder()
				.nomeCenario(cenario.getNome())
				.listaItens(PericiaResponseDTO.convertDTO(cenario.getPericias()))
				.build();
	}
}
