package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.CenarioResquestDTO;
import br.com.games101.sheet.entity.Cenario;

public interface CenarioService {

	public List<CenarioResponseDTO> listaFichas();

	public CenarioResponseDTO salvarCenario(CenarioResquestDTO cenarioResquest);

	public Optional<Cenario> buscarCenario(@NotNull Long id);

	public void excluirFicha(Long id);

	public CenarioResponseDTO atualizarCenario(Long id, @Valid CenarioResquestDTO cenarioResquest);

}
