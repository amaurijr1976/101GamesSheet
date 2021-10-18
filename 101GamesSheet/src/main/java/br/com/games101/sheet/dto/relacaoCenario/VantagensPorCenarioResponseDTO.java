package br.com.games101.sheet.dto.relacaoCenario;

import java.util.List;

import br.com.games101.sheet.dto.VantagemResponseDTO;
import br.com.games101.sheet.entity.Cenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VantagensPorCenarioResponseDTO {

	private String nomeCenario ;
	
	private List<VantagemResponseDTO> listaItens;

	static public VantagensPorCenarioResponseDTO convertDTO(Cenario cenario){
		return VantagensPorCenarioResponseDTO.builder()
				.nomeCenario(cenario.getNome())
				.listaItens(VantagemResponseDTO.convertDTO(cenario.getVantagens()))
				.build();
	}
}
