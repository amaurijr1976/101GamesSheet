package br.com.games101.sheet.dto.relacaoCenario;

import java.util.List;

import br.com.games101.sheet.dto.ItemResponseDTO;
import br.com.games101.sheet.entity.Cenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItensPorCenarioResponseDTO {

	private String nomeCenario ;
	
	private List<ItemResponseDTO> listaItens;

	static public ItensPorCenarioResponseDTO convertDTO(Cenario cenario){
		return ItensPorCenarioResponseDTO.builder()
				.nomeCenario(cenario.getNome())
				.listaItens(ItemResponseDTO.convertDTO(cenario.getItems()))
				.build();
	}
}
