package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.CenarioRequestDTO;
import br.com.games101.sheet.entity.Cenario;


public interface CenarioService {

	public List<CenarioResponseDTO> listaFichas();
	
	public CenarioResponseDTO incluiCenario(CenarioRequestDTO cenarioResquest);
	
	public List<CenarioResponseDTO> incluiCenarioLista(List<CenarioRequestDTO> cenarioResquestLista);
	
	public Optional<Cenario> buscarCenario(@NotNull Long id);

	public void excluirFicha(Long id);

	public CenarioResponseDTO atualizarCenario(Long id, @Valid CenarioRequestDTO cenarioResquest);

}
