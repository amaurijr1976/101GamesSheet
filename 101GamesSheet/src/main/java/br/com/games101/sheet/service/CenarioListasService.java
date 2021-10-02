package br.com.games101.sheet.service;

import br.com.games101.sheet.dto.relacaoCenario.ItensPorCenarioResponseDTO;

public interface CenarioListasService {

	public ItensPorCenarioResponseDTO listaItensPorCenario(long id);
	
}
