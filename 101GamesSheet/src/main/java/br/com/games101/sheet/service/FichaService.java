package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.games101.sheet.dto.FichaResponseDTO;
import br.com.games101.sheet.dto.FichaResquestDTO;

public interface FichaService {

	public List<FichaResponseDTO> listaFichas();

	public FichaResponseDTO salvarFicha(FichaResquestDTO fichaRequest);

	public FichaResponseDTO buscarFicha(@NotNull Long id);

	public boolean excluirFicha(Long id);

	public FichaResponseDTO atualizarFicha(Long id, @Valid FichaResquestDTO fichaRequest);
}
