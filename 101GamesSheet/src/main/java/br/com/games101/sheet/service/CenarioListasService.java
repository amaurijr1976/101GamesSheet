package br.com.games101.sheet.service;

import br.com.games101.sheet.dto.relacaoCenario.FeiticosPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.ItensPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.PericiasPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.VantagensPorCenarioResponseDTO;

public interface CenarioListasService {

	public ItensPorCenarioResponseDTO listaItensPorCenario(long id);
	
	public VantagensPorCenarioResponseDTO listaVantagensPorCenario(long id);
	
	public PericiasPorCenarioResponseDTO listaPericiasPorCenario(long id);
	
	public FeiticosPorCenarioResponseDTO listaFeiticosPorCenario(long id);	
}
